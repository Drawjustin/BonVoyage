import { OrbitControls } from "@react-three/drei";
import { useEffect, useRef } from "react";
import gsap from 'gsap';
import { useThree } from "@react-three/fiber";

const CameraControls = ({ position, target }) => {
  const { camera } = useThree();
  const controlsRef = useRef();

  useEffect(() => {
    if (controlsRef.current && position && target) {
      gsap.to(controlsRef.current.target, {
        duration: 1,
        x: target.x,
        y: target.y,
        z: target.z,
        ease: "power3.inOut"
      });
      gsap.to(camera.position, {
        duration: 1,
        x: position.x,
        y: position.y,
        z: position.z,
        ease: "power3.inOut"
      });
    }
  }, [position, target, camera]);

  return <OrbitControls ref={controlsRef} enableZoom={false} enableRotate={false}/>;
};

export default CameraControls;