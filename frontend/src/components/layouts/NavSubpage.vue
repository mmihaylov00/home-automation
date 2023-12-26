<script setup lang="ts">
import router from '@/views/router.js'
import { mdiArrowLeft } from '@mdi/js'

const props = defineProps<{
  defaultBack: string
}>()

function back() {
  if (router.getRoutes()[1].path !== router.currentRoute.value.path) {
    router.back()
    return
  }
  router.push(props.defaultBack)
}
</script>

<template>
  <v-row>
    <v-col cols="12">
      <v-card variant="tonal">
        <v-card-title>
          <v-btn
            :prepend-icon="mdiArrowLeft"
            @click="back"
            >Back</v-btn
          >
        </v-card-title>
      </v-card>
    </v-col>
  </v-row>
</template>

<style scoped></style>
