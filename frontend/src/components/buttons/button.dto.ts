export interface ButtonType {
  label?: string
  icon?: string
  emitOnClick: string
  color?: 'primary' | 'secondary' | 'success' | 'info' | 'warning' | 'error' | string
  type?: 'submit'
  size?: 'x-small' | 'small' | 'default' | 'large' | 'x-large'
  disabled?: boolean
  visible?: boolean
}
