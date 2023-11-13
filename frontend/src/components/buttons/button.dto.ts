export class ButtonType {
  label?: string
  icon?: string
  clickFunctionName: string
  color?: 'primary' | 'secondary' | 'success' | 'info' | 'warning' | 'error' | string
  type?: 'submit'
  size?: 'x-small' | 'small' | 'default' | 'large' | 'x-large'
  disabled?: boolean
  visible?: boolean
}
