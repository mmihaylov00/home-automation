<script setup lang="ts">
import { RouterView } from 'vue-router'
import { ref } from 'vue'

import NavDrawer from '@/components/layouts/NavDrawer.vue'
import Header from '@/components/layouts/Header.vue'

const nav = ref<typeof NavDrawer>()
</script>

<template>
  <v-app>
    <v-layout>
      <Header :nav="nav" />

      <NavDrawer ref="nav" />

      <v-main>
        <v-container class="overflow-auto h-100">
          <RouterView />
        </v-container>
      </v-main>
    </v-layout>
  </v-app>
</template>

<style lang="scss" scoped>
.v-container {
  max-width: unset !important;
}
</style>
