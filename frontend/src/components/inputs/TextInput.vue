<script setup lang="ts">
import { TextInputType } from '@/components/inputs/input.dto'

defineProps<{
  input: TextInputType
  form: any
}>()
</script>

<template>
  <v-text-field
    variant="outlined"
    :rules="input.inputRules(input, form)"
    :label="input.label"
    :clearable="input.clearable !== false"
    v-model="form[input.key]" />
</template>
