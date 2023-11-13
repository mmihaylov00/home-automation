<script setup lang="ts">
import { ref } from 'vue'
import { mdiAccountGroup, mdiCogs, mdiLightbulb, mdiCellphoneCog, mdiHistory } from '@mdi/js'

const isOpen = ref(true)

const navItems = [
  {
    title: 'Your Tasks',
    link: '/',
    icon: mdiCellphoneCog,
  },
]

const adminItems = [
  {
    title: 'Users',
    link: '/users',
    icon: mdiAccountGroup,
  },
  {
    title: 'Devices',
    link: '/devices',
    icon: mdiLightbulb,
  },
  {
    title: 'Default Tasks',
    link: '/tasks',
    icon: mdiCogs,
  },
  {
    title: 'Executed Tasks',
    link: '/executed',
    icon: mdiHistory,
  },
]

defineExpose({
  toggleDrawer: () => (isOpen.value = !isOpen.value),
})
</script>

<template>
  <v-navigation-drawer v-model="isOpen">
    <v-list class="pt-1 pe-2">
      <v-divider />
      <v-list-item
        v-for="(item, i) in navItems"
        :prepend-icon="item.icon"
        :key="i"
        :active="$route.path === item.link"
        :to="item.link">
        {{ item.title }}
      </v-list-item>
      <!--      todo is admin-->
      <template v-if="true">
        <v-divider />
        <v-list-subheader>Admin</v-list-subheader>
        <v-divider />
        <v-list-item
          v-for="(item, i) in adminItems"
          :key="i"
          :prepend-icon="item.icon"
          :active="$route.path === item.link"
          :to="item.link">
          {{ item.title }}
        </v-list-item>
      </template>
    </v-list>
  </v-navigation-drawer>
</template>

<style scoped></style>
