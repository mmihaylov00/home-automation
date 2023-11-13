<script setup lang="ts">
import ButtonRow from '@/components/buttons/ButtonRow.vue'
import { mdiContentCopy, mdiPencil, mdiPlay } from '@mdi/js'

function copy(i) {
  console.log(i)
}
function run(i) {
  console.log(i)
}
function edit(i) {
  console.log(i)
}
</script>

<template>
  <v-row>
    <v-col cols="12" md="6" lg="4" xl="4" v-for="i in [1, 2, 3, 4, 5, 6]" :key="i">
      <v-card variant="tonal" class="h-100">
        <v-card-title>#{{ i }} Task Name</v-card-title>
        <v-card-actions class="w-100 justify-space-between flex-wrap">
          <ButtonRow
            @run="run(i)"
            size="small"
            additional-class="mr-1"
            :buttons="[{ icon: mdiPlay, color: 'success', clickFunctionName: 'run' }]" />
          <ButtonRow
            @edit="edit(i)"
            @copy="copy(i)"
            size="small"
            additional-class="mr-1"
            :buttons="[
              { icon: mdiPencil, color: 'warning', clickFunctionName: 'edit' },
              { icon: mdiContentCopy, color: 'info', clickFunctionName: 'copy' },
            ]" />
        </v-card-actions>
      </v-card>
    </v-col>
  </v-row>
</template>
