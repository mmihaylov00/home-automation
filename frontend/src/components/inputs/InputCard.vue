<script setup lang="ts">
import { ref } from 'vue'
import NavDrawer from '@/components/layouts/NavDrawer.vue'
import ButtonRow from '@/components/buttons/ButtonRow.vue'
import { InputType } from '@/components/inputs/input.dto'
import { ButtonType } from '@/components/buttons/button.dto'

defineProps<{
  inputs: InputType[]
  form: any
  buttons: ButtonType[]
  title: string
}>()

const inputFormRef = ref<typeof NavDrawer>()
</script>

<template>
  <v-row>
    <v-col cols="12">
      <v-card variant="tonal">
        <v-card-title>{{ title }}</v-card-title>
        <v-form ref="inputFormRef">
          <v-row no-gutters>
            <v-col v-for="input in inputs" :cols="input.colSize || 4" class="p-0 px-4">
              <component
                v-if="input.visible == undefined || input.visible(form)"
                :is="input.component"
                :input="input"
                :form="form" />
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
      </v-card>
    </v-col>
  </v-row>
</template>
