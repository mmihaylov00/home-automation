interface BaseInputType {
  type: 'table' | 'button' | 'dropdown' | 'text'
  key: string
  label: string
  visible?: (form: BaseInputType[]) => boolean
  required?: boolean | ((form: BaseInputType[]) => boolean)
  default?: string | number | boolean
  colSize?: number
  rules?: any
  value?: unknown
}

export interface MultiValue {
  title: string
  value: string | number | boolean
}

export interface TextInputType extends BaseInputType {
  type: 'text'
  value?: string
  clearable?: boolean
}

export interface DropdownInputType extends BaseInputType {
  type: 'dropdown'
  values: MultiValue[]
  value?: MultiValue['value']
}

export interface ButtonToggleInputType extends BaseInputType {
  type: 'button'
  values: MultiValue[]
  value?: string | boolean
}

export interface TableInputColumn {
  title: string
  key: string
}

export interface TableInputType extends BaseInputType {
  type: 'table'
  columns: TableInputColumn[]
  clearable?: boolean
  value: { [key: string]: string | number | undefined }[]
  // colSize: number = 12
}

export type TableInputRecord = TableInputType['value'][number]

/**
 * Combined type with guards for each input type
 * Add a new guard and interface when there is a new input type to be defined
 */
export type InputType = TableInputType | ButtonToggleInputType | DropdownInputType | TextInputType
export type FilterType = ButtonToggleInputType | DropdownInputType | TextInputType

export function isInputTable(input: InputType): input is TableInputType {
  return input.type === 'table'
}

export function isInputButton(input: InputType): input is ButtonToggleInputType {
  return input.type === 'button'
}

export function isInputDropdown(input: InputType): input is DropdownInputType {
  return input.type === 'dropdown'
}

export function isInputText(input: InputType): input is TextInputType {
  return input.type === 'text'
}

export function inputRules(input: InputType, form: any) {
  const rules = input.rules?.required || ((value: string) => !!value || 'Field is required!')
  return [
    input.required == undefined ||
      (typeof input.required == 'function' && !input.required(form)) ||
      rules,
  ]
}

export function castFormToObject(inputs: InputType[]): { [key: string]: InputType['value'] } {
  const acc: { [key: InputType['key']]: InputType['value'] } = {}
  for (const input of inputs) {
    acc[input.key] = input.value
  }
  return acc
}

export function castFilterToObject<E>(inputs: { key: string; value: E }[]): { [key: string]: E } {
  const acc: { [key: string]: E } = {}
  for (const input of inputs) {
    acc[input.key] = input.value
  }
  return acc
}

export type InputCardForm = InputType[]
