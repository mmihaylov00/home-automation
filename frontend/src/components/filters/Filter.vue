<script setup lang="ts">
import { onMounted, ref } from 'vue'
import InputCard from '@/components/inputs/InputCard.vue'
import { InputType, TableInputType } from '@/components/inputs/input.dto'
import router from '@/views/router'
import { useRoute } from 'vue-router'

const emit = defineEmits(['filter'])

const props = defineProps<{
  inputs: InputType[]
}>()

const form: any = ref({})

const defaultPropsCopy = {}

onMounted(() => {
  if (!props.inputs) return

  props.inputs.forEach((value) => {
    if (value instanceof TableInputType) {
      form.value[value.key] = value.default || []
    } else {
      form.value[value.key] = value.default || ''
    }
  })

  Object.assign(defaultPropsCopy, form.value)

  Object.entries(useRoute().query).forEach((entry) => {
    let value: any = entry[1]
    if (['true', 'false'].includes(value)) {
      value = value == 'true'
    }
    form.value[entry[0]] = value
  })
})

function filterTenants() {
  emit('filter', form.value)
  const query = { ...form.value }
  for (const key in query) {
    if (query[key] === '') {
      delete query[key]
    }
  }
  router.replace({ query })
}

function clearFilters() {
  Object.assign(form.value, defaultPropsCopy)
  filterTenants()
}
</script>

<template>
  <InputCard
    title="Filter"
    @clear="clearFilters"
    @filter="filterTenants"
    :form="form"
    :inputs="inputs"
    :buttons="[
      { label: 'Clear', color: 'error', clickFunctionName: 'clear' },
      { label: 'Filter', clickFunctionName: 'filter', type: 'submit' },
    ]" />
</template>

<style scoped></style>
