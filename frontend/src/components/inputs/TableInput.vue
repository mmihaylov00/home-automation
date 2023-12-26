<script setup lang="ts">
import { TableInputRecord, TableInputType } from '@/components/inputs/input.dto.js'
import { nextTick, onMounted, reactive, watch } from 'vue'
import { mdiCloseThick } from '@mdi/js'

const props = defineProps<{
  input: TableInputType
  modelValue: TableInputType['value']
}>()

const tableFieldsValues = reactive<TableInputRecord[]>([])

watch(tableFieldsValues, async () => {
  // Ensure that a new item will be added if the last item in the array has a value
  if (
    Object.values(tableFieldsValues[tableFieldsValues.length - 1]).filter((value) => !value)
      .length == 0
  ) {
    addEmpty()
  }

  // Ensure that there are no unnecessary field lines
  if (
    tableFieldsValues.length > 1 &&
    Object.values(tableFieldsValues.slice(-2).flatMap((e) => Object.values(e))).every(
      (e) => e === ''
    )
  ) {
    tableFieldsValues.splice(-1)
  }
})

onMounted(() => {
  const tableInfo = props.modelValue
  if (!Array.isArray(tableInfo)) {
    return
  }

  for (const item of tableInfo) {
    let row: TableInputRecord = {}
    props.input.columns.forEach((column) => (row[column.key] = item[column.key]))
    tableFieldsValues.push(row)
  }
  addEmpty()
})

function addEmpty(position?: number) {
  let row: TableInputRecord = {}
  props.input.columns.forEach((column) => (row[column.key] = ''))
  if (position) {
    tableFieldsValues.splice(position, 0, row)
  } else {
    tableFieldsValues.push(row)
  }
}

async function remove(index: number) {
  tableFieldsValues.splice(index, 1)
}

async function onValueChange(e: Event, id: number, columnKey: string) {
  const fieldValue = (e.target as HTMLInputElement).value
  const splitBySpaces = fieldValue.split(/\s+/gm) || []
  const columns = props.input.columns.map((c) => c.key)

  let currentId = id
  let currentColumnKey = columnKey
  let field = e.target as HTMLInputElement

  // Set value to current field
  tableFieldsValues[currentId][currentColumnKey] = splitBySpaces.shift()

  // Set values for all other fields, separated by space
  for (const inputPart of splitBySpaces) {
    // Calculate next key
    currentColumnKey = columns[columns.indexOf(currentColumnKey) + 1]

    // Append blank space in next position
    if (!currentColumnKey) {
      addEmpty(currentId + 1)
      currentId += 1
      currentColumnKey = columns[0]
    }

    // Ensure that always the last field is focused
    await nextTick()
    field = getNeighbourField(field)
    field.focus()

    // Update value
    tableFieldsValues[currentId][currentColumnKey] = inputPart
  }
}

/**
 * Finds the next or previous text field and moves the cursor focus to that item
 */
function getNeighbourField(
  elem: HTMLInputElement,
  neighbour: 'next' | 'previous' = 'next'
): HTMLInputElement {
  const items = Array.from(elem.form?.elements || []).filter(
    (e) => e.getAttribute('type') === 'text'
  )
  const currentIndex = items.indexOf(elem)
  return items[currentIndex + (neighbour === 'next' ? 1 : -1)] as HTMLInputElement
}

function onPressEnter(e: Event) {
  // Ensure that the field has a non empty value before focusing the next item
  const fieldValue = (e.target as HTMLInputElement).value
  if (fieldValue.length) {
    getNeighbourField(e.target as HTMLInputElement)?.focus()
  }
}

function onPressBackspace(e: Event) {
  // If the field is empty, focus on the previous field
  const fieldValue = (e.target as HTMLInputElement).value
  if (!fieldValue.length) {
    e.preventDefault()
    getNeighbourField(e.target as HTMLInputElement, 'previous')?.focus()
  }
}
</script>

<template>
  <v-table
    density="comfortable"
    style="width: 100%">
    <thead>
      <tr>
        <td></td>
        <td
          v-for="(column, index) in input.columns"
          :key="index">
          {{ column.title }}
        </td>
      </tr>
    </thead>
    <tbody>
      <tr
        v-for="(row, index) in tableFieldsValues"
        :key="index">
        <td style="width: 2%">
          {{ index + 1 }}
        </td>
        <td
          style="width: 48%"
          colspan="1"
          v-for="(column, cIndex) of input.columns"
          :key="cIndex"
          class="pa-0 px-1">
          <v-text-field
            variant="solo-filled"
            class="my-1"
            hide-details
            @keydown.backspace="(e: Event) => onPressBackspace(e)"
            @input="(e: Event) => onValueChange(e, index, column.key)"
            @keyup.enter="(e: Event) => onPressEnter(e)"
            :clearable="input.clearable !== false"
            :placeholder="column.title"
            v-model="tableFieldsValues[index][column.key]" />
        </td>
        <td style="width: 2%">
          <v-hover v-if="index < tableFieldsValues.length - 1">
            <template v-slot:default="{ isHovering, props }">
              <v-btn
                tabindex="-1"
                v-bind="props"
                size="x-small"
                color="error"
                :icon="mdiCloseThick"
                :class="{ 'on-hover': isHovering }"
                @click="() => remove(index)"
                variant="flat">
              </v-btn>
            </template>
          </v-hover>
        </td>
      </tr>
    </tbody>
  </v-table>
</template>

<style scoped lang="scss">
.v-btn {
  scale: 0.7;
  transition: all 0.3s ease-in-out;

  &.on-hover {
    scale: 0.9;
  }

  &:not(.on-hover) {
    opacity: 0.7;
  }
}

td {
  border-bottom: none !important;
}
</style>
