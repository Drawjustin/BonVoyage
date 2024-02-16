'use client'
import React from 'react';
import { PlaneGeometry, MeshBasicMaterial, Mesh } from 'three';

const Ocean = () => {
    const planeGeometry = new PlaneGeometry(1500, 600, 1, 1);
    const planeMaterial = new MeshBasicMaterial({ color: "#54A0E3" }); // Sky Blue color, you can change it
    const planeMesh = new Mesh(planeGeometry, planeMaterial);
  
    return (
      <mesh>
        <primitive object={planeMesh} position={[0, -5, 0]} rotation={[-Math.PI / 2, 0, 0]} />
      </mesh>
    );
};

export default Ocean

// import React, { useMemo } from 'react';
// import { PlaneGeometry, MeshBasicMaterial, Mesh, ShaderMaterial, Vector3  } from 'three';

// const Ocean = () => {
//     const planeGeometry = new PlaneGeometry(1000, 300, 1, 1);
//     const planeMaterial = new MeshBasicMaterial({ color: "#54A0E3" }); // Sky Blue color, you can change it
//     const planeMesh = new Mesh(planeGeometry, planeMaterial);

//     const shaderMaterial = useMemo(() => {
//       const shader = {
//         uniforms: {
//             colorTop: { value: new Vector3(255, 255, 255) },
//             colorBottom: { value: new Vector3(84, 160, 227)  }
//         },
//         vertexShader: `
//             varying vec2 vUv;
//             void main() {
//                 vUv = uv;
//                 gl_Position = projectionMatrix * modelViewMatrix * vec4(position, 1.0);
//             }
//         `,
//         fragmentShader: `
//             varying vec2 vUv;
//             uniform vec3 colorTop;
//             uniform vec3 colorBottom;
//             void main() {
//                 // Linear gradient from top to bottom
//                 gl_FragColor = vec4(mix(colorTop / 255.0, colorBottom / 255.0, vUv.y), 1.0);
//             }
//         `
//       };
//       return new ShaderMaterial(shader);
//   }, []);

//     return (
//       <mesh>
//         <primitive object={new Mesh(planeGeometry, shaderMaterial)} position={[0, -5, 0]} rotation={[-Math.PI / 2, 0, 0]} />
//       </mesh>
//     );
// };

// export default Ocean