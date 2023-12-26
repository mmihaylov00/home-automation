<script setup lang="ts">
import NavDrawer from '@/components/layouts/NavDrawer.vue'
import { mdiAccount, mdiWeatherNight, mdiWhiteBalanceSunny } from '@mdi/js'
import { useThemeStore } from '@/stores/theme.store.js'

defineProps<{
  nav: typeof NavDrawer | undefined
}>()

const themeStore = useThemeStore()
</script>

<template>
  <v-app-bar>
    <template v-slot:prepend>
      <v-app-bar-nav-icon @click.stop="nav?.toggleDrawer()"></v-app-bar-nav-icon>
    </template>
    <template v-slot:append>
      <v-switch
        v-model="themeStore.isDark"
        v-on:click="themeStore.toggle()"
        hide-details
        inset
        color="indigo"
        :true-icon="mdiWeatherNight"
        :false-icon="mdiWhiteBalanceSunny"></v-switch>
      <v-btn
        class="ma-2"
        variant="elevated"
        height="36"
        :icon="mdiAccount"
        color="primary"></v-btn>
    </template>
  </v-app-bar>
</template>

<style scoped></style>
