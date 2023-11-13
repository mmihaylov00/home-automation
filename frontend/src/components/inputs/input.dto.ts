import TextInput from '@/components/inputs/TextInput.vue'
import DropdownInput from '@/components/inputs/DropdownInput.vue'
import ButtonToggleInput from '@/components/inputs/ButtonToggleInput.vue'
import type { Component } from 'vue'

export abstract class InputType {
  abstract readonly component: Component
  key: string
  label: string
  visible?: (form: any) => boolean
  required?: boolean | ((form: any) => boolean)
  default?: string | number
  colSize: number = 4

  constructor(data: InputType) {
    this.key = data.key
    this.label = data.label
    this.visible = data.visible
    this.required = data.required
    this.default = data.default
    this.colSize = data.colSize
  }

  readonly rules = {
    required: (value: string) => !!value || 'Field is required!',
  }

  public inputRules(input: InputType, form: any) {
    return [
      input.required == undefined ||
        (typeof input.required == 'function' && !input.required(form)) ||
        this.rules.required,
    ]
  }
}

export class MultiValue {
  title: string
  value: string | boolean
}

export class TextInputType extends InputType {
  readonly component = TextInput
  value?: string
  clearable?: boolean

  constructor(data: TextInputType) {
    super(data)
    this.value = data.value
    this.clearable = data.clearable
  }
}

export class DropdownInputType extends InputType {
  readonly component = DropdownInput
  values: MultiValue[]

  constructor(data: DropdownInputType) {
    super(data)
    this.values = data.values
  }
}

export class ButtonToggleInputType extends InputType {
  readonly component = ButtonToggleInput
  values: MultiValue[]

  constructor(data: ButtonToggleInputType) {
    super(data)
    this.values = data.values
  }
}

export class TableInputColumn {
  title: string
  key: string
}

export class TableInputType extends InputType {
  readonly component = TableInput
  columns: TableInputColumn[]
  colSize: number = 12

  constructor(data: TableInputType) {
    super(data)
    this.columns = data.columns
  }
}
