<script setup lang="ts">
import { onMounted } from 'vue'
import InputCard from '@/components/inputs/InputCard.vue'
import { FilterType, castFilterToObject } from '@/components/inputs/input.dto.js'
import router from '@/views/router.js'
import { useRoute } from 'vue-router'
import { reactive } from 'vue'

const props = defineProps<{
  inputs: FilterType[]
}>()
const emit = defineEmits(['filter', 'update:inputs'])
const computedInputs = reactive([...props.inputs])

onMounted(() => {
  if (!computedInputs) return
  resetToDefault()

  // Fill in all filter properties from URL
  Object.entries(useRoute().query).forEach((entry) => {
    const key = entry[0]
    const rawValue = entry[1]?.toString() || ''
    const value = ['true', 'false'].includes(rawValue) ? rawValue === 'true' : rawValue

    const field = computedInputs.find((input) => input.key === key)
    if (field) {
      field.value = value
    }
  })
})

function applyFilter() {
  const formItems = computedInputs
    .filter((e) => e.value !== '')
    .map((e) => ({ ...e, value: e.value?.toString() }))
  const query = castFilterToObject(formItems)

  emit('filter', query)
  router.replace({ query })
}

const resetToDefault = () => {
  for (const input of computedInputs) {
    input.value = 'default' in input ? input.default : ''
  }
}

function clearFilters() {
  resetToDefault()
  applyFilter()
}
</script>

<template>
  <InputCard
    title="Filter"
    @clear="clearFilters"
    @filter="applyFilter"
    :form="computedInputs"
    :buttons="[
      { label: 'Clear', emitOnClick: 'clear', color: 'error' },
      { label: 'Filter', emitOnClick: 'filter', type: 'submit' },
    ]" />
</template>

<style scoped></style>
