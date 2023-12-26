import { computed, ref } from 'vue'
import { defineStore } from 'pinia'
import { useTheme } from 'vuetify'

export const useThemeStore = defineStore('theme', () => {
  const theme = useTheme()
  const isDark = ref(localStorage.darkTheme !== 'false' && theme.global.current.value.dark)
  const mode = computed(() => (isDark.value ? 'dark' : 'light'))

  function change(value: boolean) {
    isDark.value = value
    theme.global.name.value = value ? 'dark' : 'light'
    localStorage.darkTheme = value
  }

  function toggle() {
    change(!isDark.value)
  }

  change(isDark.value)

  return { isDark, mode, change, toggle }
})
