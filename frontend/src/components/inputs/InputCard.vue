<script setup lang="ts">
import { ref } from 'vue'
import ButtonRow from '@/components/buttons/ButtonRow.vue'
import { InputType } from '@/components/inputs/input.dto.js'
import { ButtonType } from '@/components/buttons/button.dto.js'

import TextInput from '@/components/inputs/TextInput.vue'
import DropdownInput from '@/components/inputs/DropdownInput.vue'
import ButtonToggleInput from '@/components/inputs/ButtonToggleInput.vue'
import type { Component } from 'vue'
import TableInput from '@/components/inputs/TableInput.vue'
import { computed } from 'vue'
import { VForm } from 'vuetify/components'

const props = defineProps<{
  form: InputType[]
  buttons: ButtonType[]
  title: string
}>()

const emit = defineEmits(['update:form'])

const inputFormRef = ref<typeof VForm>()
const mapInputToComponent = (input: InputType): Component => {
  return {
    table: TableInput,
    button: ButtonToggleInput,
    dropdown: DropdownInput,
    text: TextInput,
  }[input.type]
}

const computedInputs = computed({
  get() {
    return props.form
  },
  set(value) {
    emit('update:form', value)
  },
})

const getSizeProperties = (input: InputType) => {
  const colSize =
    input.colSize ||
    {
      table: 12,
      button: 4,
      dropdown: 4,
      text: 4,
    }[input.type] ||
    4

  return {
    sm: (colSize > 6 ? colSize : 6) || 6,
    md: (colSize > 4 ? colSize : 4) || 4,
    lg: input.colSize || 4,
  }
}

function onChange(input: InputType, newValue: InputType['value']) {
  input.value = newValue
  emit('update:form', computedInputs)
}
</script>

<template>
  <v-row>
    <v-col cols="12">
      <v-card variant="tonal">
        <v-card-title>{{ title }}</v-card-title>
        <v-sheet
          rounded="lg"
          elevation="1"
          class="pt-3">
          <v-form ref="inputFormRef">
            <v-row dense>
              <v-col
                v-for="(input, index) in computedInputs"
                cols="12"
                v-bind="getSizeProperties(input)"
                :key="index"
                class="p-0 px-4">
                <component
                  v-if="input.visible == undefined || input.visible(form)"
                  :is="mapInputToComponent(input)"
                  :input="input"
                  :model-value="input.value"
                  @update:model-value="
                    (newValue: InputType['value']) => onChange(input, newValue)
                  " />
              </v-col>
            </v-row>
          </v-form>
          <v-row class="mt-0">
            <v-col cols="12">
              <ButtonRow
                :buttons="buttons"
                :form-ref="inputFormRef"
                v-bind="$attrs"
                class="mb-4 float-right" />
            </v-col>
          </v-row>
        </v-sheet>
      </v-card>
    </v-col>
  </v-row>
</template>
