import React from 'react';
import { PlaneGeometry, MeshBasicMaterial, Mesh } from 'three';

const Ocean = () => {
    const planeGeometry = new PlaneGeometry(1000, 5000, 1, 1);
    const planeMaterial = new MeshBasicMaterial({ color: "#54A0E3" }); // Sky Blue color, you can change it
    const planeMesh = new Mesh(planeGeometry, planeMaterial);
  
    return (
      <>
        <primitive object={planeMesh} position={[0, -3, 0]} rotation={[-Math.PI / 2, 0, 0]} />
      </>
    );
};

export default Ocean