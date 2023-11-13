<script setup lang="ts">
import { DropdownInputType } from '@/components/inputs/input.dto'

defineProps<{
  input: DropdownInputType
  form: any
}>()
</script>

<template>
  <v-select
    variant="outlined"
    :rules="input.inputRules(input, form)"
    :label="input.label"
    :items="input.values"
    v-model="form[input.key]" />
</template>