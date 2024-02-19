import React from 'react'
import ReactDOM from 'react-dom/client'
import { Provider } from 'react-redux'
import App from './App.jsx'
import './App.scss'
import store from './store'
import { PlayProvider } from './components/Threejs/contexts/PlayProvider.jsx'
// import Navbar from './components/Navbar/Navbar.tsx'

ReactDOM.createRoot(document.getElementById('root')).render(
  <PlayProvider>
    <Provider store={store}>
        <App />
    </Provider>
  </PlayProvider>
  ,
)
