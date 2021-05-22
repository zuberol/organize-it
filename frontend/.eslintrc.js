module.exports = {
  'env': {
  'browser': true,
  'es2021': true,
  },
  'extends': [
  'plugin:react/recommended',
  'google'
  ],
  'parserOptions': {
  'ecmaFeatures': {
    'jsx': true,
  },
  'ecmaVersion': 12,
  'sourceType': 'module',
  },
  'plugins': [
  'react',
  'only-warn',
  'react-hooks'
  ],
  "rules": {
  'max-len': 'off',
  'react-hooks/rules-of-hooks': 'error', // Sprawdza stosowanie zasad hooków
  'react-hooks/exhaustive-deps': 'warn' // Sprawdza zależności efektów
  }
};
