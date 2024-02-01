'use client'

import React from 'react';
import styles from './ThreejsMainPage.module.scss';
import { Canvas } from "@react-three/fiber";
// import Experience from "../../components/Threejs/Experience";
import { ScrollControls } from '@react-three/drei';
import { EffectComposer, Noise } from '@react-three/postprocessing';
// import Overlay from "../../components/Threejs/Overlay";
import { usePlay } from "../../components/Threejs/contexts/PlayProvider";
import dynamic from 'next/dynamic';

const Experience = dynamic(() => import("../../components/Threejs/Experience"), { ssr: false });
const Overlay = dynamic(() => import("../../components/Threejs/Overlay"), { ssr: false });

const ThreejsMainPage = () => {
    const { play, end } = usePlay();

    return (
        <div className={styles.container}>
        <Canvas>
            <color attach="background" args={["#ececec"]} />
            <ScrollControls 
                pages={play && !end ? 20:0} 
                damping={0.5}
                style={{
                    top: "10px",
                    left: "0px",
                    bottom: "10px",
                    right: "10px",
                    width: "auto",
                    height: "auto",
                    animation: "fadeIn 2.4s ease-in-out 1.2s forwards",
                    opacity: 0,
                }}
            >
                <Experience />
            </ScrollControls>
            <EffectComposer>
                <Noise opacity={0.2} />
            </EffectComposer>
        </Canvas>
        <Overlay />
        </div>
    )
}


export default ThreejsMainPage;