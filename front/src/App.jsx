import './App.css'
import React from 'react'

import { BrowserRouter, Route, Routes } from 'react-router-dom'
import SignupPage from './pages/SignupPage'
import SignupArtistPage from './pages/SignupArtistPage'
import SignupMemberPage from './pages/SignupMemberPage'
import ArtistHomePage from './pages/ArtistHomePage'
import Mainpage3 from './pages/MainPage3'
import AuctionLivePage from './pages/AuctionLivePage'
import AfterAuctionPage from './pages/AfterAuctionPage'
import '/quill.custom.css'

function App() {
  // const [count, setCount] = useState(0)

  return (
    // <>
    //   <div>
    //     <a href="https://vitejs.dev" target="_blank">
    //       <img src={viteLogo} className="logo" alt="Vite logo" />
    //     </a>
    //     <a href="https://react.dev" target="_blank">
    //       <img src={reactLogo} className="logo react" alt="React logo" />
    //     </a>
    //   </div>
    //   <h1>Vite + React</h1>
    //   <div className="card">
    //     <button onClick={() => setCount((count) => count + 1)}>
    //       count is {count}
    //     </button>
    //     <p>
    //       Edit <code>src/App.jsx</code> and save to test HMR
    //     </p>
    //   </div>
    //   <p className="read-the-docs">
    //     Click on the Vite and React logos to learn more
    //   </p>

    // </>
      <BrowserRouter>
        <Routes>
          <Route path="/signup" element={<SignupPage />} />
          <Route path="/signup/artist" element={<SignupArtistPage />} />
          <Route path="/signup/member" element={<SignupMemberPage />} />
          <Route path="/artistpage" element={<ArtistHomePage />} />
          <Route path="/mainpage3" element={<Mainpage3 />} />
          <Route path="/auctionlive" element={<AuctionLivePage />} />
          <Route path="/afterauction" element={<AfterAuctionPage />} />
        </Routes>
      </BrowserRouter>
  )
}

export default App
