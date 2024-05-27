/*
Auto-generated by: https://github.com/pmndrs/gltfjsx
Command: npx gltfjsx@6.2.16 public/models/text/text2.glb 
*/

import React, { useRef } from 'react'
import { useGLTF } from '@react-three/drei'

const Text2 = (props) => {
  const { nodes, materials } = useGLTF('./models/text/text2_3.glb')
  return (
    <group {...props} dispose={null}>
      <mesh geometry={nodes.텍스트001.geometry} material={materials.매테리얼} position={[0.003, 0.527, 0.061]} rotation={[1.527, 0, 0]} />
    </group>
  )
}

useGLTF.preload('./models/text/text2_3.glb')

export default Text2;