'use client'
import {Float} from "@react-three/drei";
import Island_1 from "@/components/ThreejsTest/Island_1";
import Island_2 from "@/components/ThreejsTest/Island_2";
import Island_3 from "@/components/ThreejsTest/Island_3";
import Island_4 from "@/components/ThreejsTest/Island_4";
import Ship1 from "@/components/ThreejsTest/Ship1";
import React, { useEffect, useRef, useState } from 'react';
import { useFrame, useThree } from '@react-three/fiber';
import { gsap } from 'gsap';
import * as THREE from 'three';
import CameraControls from './Camera';
import Text1 from "./text/Text1"
import Text2 from "./text/Text2"
import Text3 from "./text/Text3"
import Text4 from "./text/Text4"

const Experience = ({ onIslandSelect, selectedIsland }) => {
    // const [selectedIsland, setSelectedIsland] = useState(null);
    const [target, setTarget] = useState(new THREE.Vector3(0, 0, 0));
    const [position, setPosition] = useState(new THREE.Vector3(0, 1, 14));

    const handleIslandClick = (island) => {
        // setSelectedIsland(island);
        onIslandSelect(island);
    };

    useEffect(() => {
        if (selectedIsland === null) {
            // 선택된 섬이 없을 때(default 값일 때) 카메라 타겟과 위치를 변경
            setTarget(new THREE.Vector3(0, 0, 0));
            setPosition(new THREE.Vector3(0, 1, 14));
        } else {
            // 선택된 섬에 따라 카메라 타겟과 위치 설정
            switch (selectedIsland) {
                case 1:
                    setTarget(new THREE.Vector3(-18, -1, -25));
                    setPosition(new THREE.Vector3(-10, 3, -9));
                    break;
                case 2:
                    setTarget(new THREE.Vector3(-13, -2, -90));
                    setPosition(new THREE.Vector3(-6, 2, -55));
                    break;
                case 3:
                    setTarget(new THREE.Vector3(25, -1, -80));
                    setPosition(new THREE.Vector3(8, 3, -55));
                    break;
                case 4:
                    setTarget(new THREE.Vector3(25, -1, -23));
                    setPosition(new THREE.Vector3(10, 3, -8));
                    break;
                default:
                    break;
            }
        }
    }, [selectedIsland]);

    return (
        <>
            {/* <OrbitControls enableZoom={false}/> */}
            {/* LIGHTS */}
            <ambientLight intensity={2} />
            <directionalLight
                position={[5, 5, 5]}
                intensity={2}
                castShadow
                color={"#9e69da"}
            />

            <CameraControls target={target} position={position}/>
            <Island_1
                scale={[0.09, 0.09, 0.09]}
                position={[-25, -5, -25]}
                onClick={() => handleIslandClick(1)}
            />
            <Text1 position={[-25, 3.5, -26]} scale={[1.3, 1.3, 1.3]} rotation-y={0.3}/>

            <Island_2
                scale={[0.08, 0.08, 0.08]}
                position={[-15, -6, -80]}
                onClick={() => handleIslandClick(2)}
            />
            <Text2 position={[-13.5, 5, -73]} scale={[1.5, 1.5, 1.5]} rotation-y={0.3}/>

            <Island_3
                scale={[0.2, 0.2, 0.2]}
                position={[15, -5, -80]}
                onClick={() => handleIslandClick(3)}
            />
            <Text3 position={[13.5, 5, -73]} scale={[1.5, 1.5, 1.5]} rotation-y={-0.3}/>

            <Island_4
                scale={[1.5, 1.5, 1.5]}
                position={[25, -5, -25]}
                onClick={() => handleIslandClick(4)}
            />
            <Text4 position={[21, 3.5, -27]} scale={[1.3, 1.3, 1.3]} rotation-y={-0.3}/>
            
            <Float floatIntensity={1} speed={3} rotationIntensity={0.2}>
                <Ship1 position={[0, -5.5, 0]} rotation-y={78.5} scale={[1.5, 1.5, 1.5]}/>
            </Float>
        </>
    )
}

export default Experience