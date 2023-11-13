<script setup lang="ts">
import { TableInputType } from '@/components/inputs/input.dto'
import { nextTick, onMounted, reactive, watch } from 'vue'
import { mdiCloseCircle } from '@mdi/js'

const props = defineProps<{
  input: TableInputType
  form: any
}>()

const data = reactive<any>([])

watch(data, async () => {
  if (Object.values(data[data.length - 1]).filter((value) => !value).length == 0) {
    addEmpty()
  }
})

onMounted(() => {
  for (const item of props.form[props.input.key]) {
    let row: any = {}
    props.input.columns.forEach((column) => (row[column.key] = item[column.key]))
    data.push(row)
  }
  addEmpty()
})

function addEmpty() {
  let row: any = {}
  props.input.columns.forEach((column) => (row[column.key] = ''))
  data.push(row)
}

async function remove(index: number) {
  data.splice(index, 1)
}

async function type(e: InputEvent, id) {
  const split = e.target._value.split(/\s+/gm)
  let len = split.length
  if (len > 2) len++

  for (let i = 0; i < len; i++) {
    const element = document.getElementById(`td-${id + i}`)
    if (!element) {
      i--
      addEmpty()
      await nextTick()
      continue
    }
    let property = JSON.parse(element.attributes['property'].value)
    data[property['index']][property['columnKey']] = split[i] || ''
    element.focus()
  }
}

function getJsonProperty(index, key) {
  return `{ "index": ${index}, "columnKey": "${key}" }`
}
</script>

<template>
  <h3>{{ input.label }}</h3>
  <v-table density="comfortable">
    <thead>
      <tr>
        <td></td>
        <td v-for="column in input.columns">
          {{ column.title }}
        </td>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(row, index) in data">
        <td style="width: 2%">
          {{ index + 1 }}
        </td>
        <td
          style="width: 48%"
          colspan="1"
          v-for="(column, cIndex) of input.columns"
          class="pa-0 px-1">
          <v-text-field
            :id="`td-${index * input.columns.length + cIndex}`"
            :property="getJsonProperty(index, column.key)"
            variant="solo-filled"
            hide-details
            class="my-1"
            @input="(e) => type(e, index * input.columns.length + cIndex)"
            :clearable="input.clearable !== false"
            :placeholder="column.title"
            v-model="data[index][column.key]" />
        </td>
        <td style="width: 2%">
          <v-hover v-if="index < data.length - 1">
            <template v-slot:default="{ isHovering, props }">
              <v-chip
                v-bind="props"
                rounded
                color="error"
                size="0"
                :class="{ 'on-hover': isHovering }"
                style="padding: 3px"
                @click="() => remove(index)"
                variant="flat">
                <v-icon size="14" color="white" :icon="mdiCloseCircle" />
              </v-chip>
            </template>
          </v-hover>
        </td>
      </tr>
    </tbody>
  </v-table>
</template>

<style scoped lang="scss">
h3 {
  font-size: 20px;
  font-weight: unset;
}

.v-chip {
  transition: all 0.3s ease-in-out;

  &.on-hover {
    scale: 1.2;
  }

  &:not(.on-hover) {
    opacity: 0.7;
  }
}

td {
  border-bottom: none !important;
}
</style>
