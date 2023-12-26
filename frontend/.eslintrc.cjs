/* eslint-env node */
require('@rushstack/eslint-patch/modern-module-resolution')

module.exports = {
  root: true,
  extends: [
    'plugin:vue/vue3-essential',
    'eslint:recommended',
    '@vue/eslint-config-typescript',
    '@vue/eslint-config-prettier/skip-formatting',
  ],
  parserOptions: {
    ecmaVersion: 'latest',
  },
  ignorePatterns: ['.eslintrc.cjs', 'dist/'],
  rules: {
    'vue/multi-word-component-names': 'off',
    "vue/new-line-between-multi-line-property": ["error", {
      "minLineOfMultilineProperty": 2
    }]
  },
}
