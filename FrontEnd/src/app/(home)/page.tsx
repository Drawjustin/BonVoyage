'use client'
import { Canvas } from "@react-three/fiber";
import React, { Suspense, useMemo, useState, useEffect } from "react";
import Experience from "../../components/ThreejsTest/Experience";
import styles from "./MainPage.module.scss"
import Ocean from "../../components/ThreejsTest/Ocean"
import IslandHtmlOverlay from "../../components/ThreejsTest/IslandHtmlOverlay";
import * as THREE from 'three';
import Cloud from "../../components/ThreejsTest/Cloud"

const ThreejsTestPage = () => {
  const [selectedIsland, setSelectedIsland] = useState(null);
  const [showOverlay, setShowOverlay] = useState(false);
  // const [start, setStart] = useState(true);

  const handleIslandSelect = (island: any) => {
    setSelectedIsland(island);
    setShowOverlay(true);
  };

  const handleClearSelection = () => {
    setSelectedIsland(null);
    setShowOverlay(false);
  };

  return (
    <div className={styles.container}>
      {/* {start ? (
        <StartPage onStartButtonClick={handleStartButtonClick} />
      ) : (
      <> */}
        {/* <div className="canvas-background" style={{ zIndex: -1 }} /> */}
        <Canvas 
          shadows 
          camera={{ position: [0, 1, 14], fov: 45 }}
          style={{
            background: "linear-gradient(to bottom, #dbecfb -8%, #ffffff)"
          }}
        >
            <Experience onIslandSelect={handleIslandSelect} selectedIsland={selectedIsland}/>
            <Ocean />
            <Cloud position={[40, 25, -120]} speed={2} amplitude={0.8}/>
        </Canvas>
        {showOverlay && (
          <div className={styles.clearSelection} onClick={handleClearSelection}>
            x
          </div>
        )}
        {showOverlay && <IslandHtmlOverlay selectedIsland={selectedIsland} />}
        {/* </>
        )} */}
      </div>
  );
}

export default ThreejsTestPage;