module.exports = {
    root: true,
    env: {
        node: true,
    },
    extends: [
        "plugin:vue/vue3-essential",
        "eslint:recommended",
    ],
    parserOptions: {
        parser: "@babel/eslint-parser",
        requireConfigFile: false,
    },
    rules: {
        "vue/no-parsing-error": [2, { "x-invalid-end-tag": false }],
        "no-mixed-spaces-and-tabs": 0, // disable rule
    },
    globals: {
        $: true,
        jQuery: true
      }
}