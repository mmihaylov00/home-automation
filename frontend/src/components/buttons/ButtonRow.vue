<script setup lang="ts">
import { ButtonType } from '@/components/buttons/button.dto'
import { VForm } from 'vuetify/components'

defineProps<{
  buttons: ButtonType[]
  formRef?: typeof VForm | undefined
  additionalClass?: string
  size?: string
}>()
</script>

<template>
  <div>
    <template v-for="button in buttons">
      <v-btn
        variant="elevated"
        v-if="button.visible != false"
        :size="button.size || size"
        :color="button.color"
        :type="button.type"
        :icon="button.label ? undefined : button.icon"
        :prepend-icon="button.icon && button.label ? button.icon : undefined"
        :disabled="button.disabled"
        :content="button.label"
        :class="additionalClass || 'mr-4'"
        @click.stop="
          async (e: Event) => {
            e.preventDefault()
            if (button.type == 'submit') {
              const { valid } = await formRef?.validate()
              if (valid) $emit(button.clickFunctionName, valid)
              return
            }
            $emit(button.clickFunctionName, true)
          }
        ">
        <template v-slot:default v-if="button.label">{{ button.label }}</template>
      </v-btn>
    </template>
  </div>
</template>

<style scoped></style>
