import './styles/main.scss'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from '@/views/router'
import themes from '@/themes/themes'

// Vuetify
import '@/styles/settings.scss'
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import { md3 } from 'vuetify/blueprints'
import { aliases, mdi } from 'vuetify/iconsets/mdi-svg'
import * as components from 'vuetify/components'

//Highcharts
import Highcharts from 'highcharts'
import HighchartsVue from 'highcharts-vue'
import Sankey from 'highcharts/modules/sankey'
import Organization from 'highcharts/modules/organization'

const vuetify = createVuetify({
  theme: {
    defaultTheme: 'dark',
    themes,
  },
  blueprint: md3,
  //https://vuetifyjs.com/en/features/icon-fonts/#mdi-icon-search
  icons: {
    defaultSet: 'mdi',
    aliases,
    sets: { mdi },
  },
  components: {
    ...components,
  },
})

//Highcharts
Sankey(Highcharts) // Needed to create an organization chart
Organization(Highcharts)

createApp(App)
  .use(vuetify)
  .use(router)
  .use(createPinia())
  .use(HighchartsVue as any)
  .mount('#app')
