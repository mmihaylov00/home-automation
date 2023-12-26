<script setup lang="ts">
import { TextInputType } from '@/components/inputs/input.dto.js'
import { inputRules } from './input.dto.js'
import { computed } from 'vue'

const props = defineProps<{
  input: TextInputType
  modelValue: TextInputType['value']
}>()

const emit = defineEmits(['update:modelValue'])

const value = computed({
  get() {
    return props.modelValue
  },
  set(value) {
    emit('update:modelValue', value)
  },
})
</script>

<template>
  <v-text-field
    variant="outlined"
    :rules="inputRules(input, modelValue)"
    :label="input.label"
    :clearable="input.clearable !== false"
    v-model="value" />
</template>
