module.exports = {
  root: true,
  env: { browser: true, es2020: true },
  extends: [
    'eslint:recommended',
    'plugin:react/recommended',
    'plugin:react/jsx-runtime',
    'plugin:react-hooks/recommended',
  ],
  ignorePatterns: ['dist', '.eslintrc.cjs'],
  parserOptions: { ecmaVersion: 'latest', sourceType: 'module' },
  settings: { react: { version: '18.2' } },
  plugins: ['react-refresh'],
  rules: {
    'react/prop-types': "off",
    'react-refresh/only-export-components': [
      'warn',
      { allowConstantExport: true },
    ],
    "react/no-unknown-property": ["error", 
    { "ignore": ["attach", "args", "geometry", "dispose", "material", "position", "transparent", 
    "position-y", "intensity", "envMapIntensity", "onBeforeCompile"]}],
    "react/react-in-jsx-scope": "off"
  },
  "parser": "@babel/eslint-paeser",
  
  // 이거 추가해야 react import할 떄 오류 안나더라~
  "extends" : ['eslint:recommended', 'plugin:react/recommended']
}
