import { OrbitControls } from "@react-three/drei";
import { useThree } from "@react-three/fiber";
import { useRef } from "react";
import { Vector3 } from "three";

 const CameraControls = () => {
   //Initialize camera controls
   const {
     camera,
     gl: { domElement },
   } = useThree();
   const ref = useRef(null);

   // Determines camera up Axis
   camera.up = new Vector3(0, 1, 0);

   // return the controls object   
   return (
     <OrbitControls
       ref={ref}
       args={[camera, domElement]}
       panSpeed={1}
       maxPolarAngle={Math.PI / 2}
     />
   );
 };

export { CameraControls };