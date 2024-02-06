'use client'
import { useRouter } from 'next/navigation';
import { Float, PerspectiveCamera, useScroll, Html, OrbitControls } from "@react-three/drei";
import { useEffect, useLayoutEffect, useMemo, useRef, Suspense } from "react";
import { TextGeometry } from 'three/addons/geometries/TextGeometry.js';
import { FontLoader } from 'three/addons/loaders/FontLoader.js';
import { useFrame, extend } from "@react-three/fiber";
import { Group, Vector3, Euler } from "three";
import { Scene, Mesh, Object3D, MeshStandardMaterial } from 'three';
import * as THREE from "three";
import { gsap } from "gsap";
import { usePlay } from "../Threejs/contexts/PlayProvider"
import dynamic from 'next/dynamic';

const LINE_NB_POINTS = 1000;
const CURVE_DISTANCE = 250;
const CURVE_AHEAD_CAMERA = 0.008;
const CURVE_AHEAD_SHIP = 0.02;
const SHIP_MAX_ANGLE = 35;
const FRICTION_DISTANCE = 42;

const Background = dynamic(() => import("./Background"), { ssr: false });
const Ship = dynamic(() => import("./Ship"), { ssr: false });
// const Cloud = dynamic(() => import("./Cloud"), { ssr: false });
const Island_1 = dynamic(() => import("./Island_1"), { ssr: false });
const Island_2 = dynamic(() => import("./Island_2"), { ssr: false });
const Island_3 = dynamic(() => import("./Island_3"), { ssr: false });
const Island_4 = dynamic(() => import("./Island_4"), { ssr: false });
const Review_Text = dynamic(() => import("./Review_text"), { ssr: false });
const Auction_Text = dynamic(() => import("./Auction_text"), { ssr: false });
const Artist_Text = dynamic(() => import("./Artist_text"), { ssr: false });
const Rock_1 = dynamic(() => import("./Rock_1"), { ssr: false });

const Experience = () => {
  const curvePoints = useMemo(
    () => [
      new THREE.Vector3(0, 0, 0),
      new THREE.Vector3(0, 0, -CURVE_DISTANCE),
      new THREE.Vector3(100, 0, -2 * CURVE_DISTANCE),
      new THREE.Vector3(-100, 0, -3 * CURVE_DISTANCE),
      new THREE.Vector3(100, 0, -4 * CURVE_DISTANCE),
      new THREE.Vector3(0, 0, -5 * CURVE_DISTANCE),
      new THREE.Vector3(0, 0, -6 * CURVE_DISTANCE),
      new THREE.Vector3(0, 0, -7 * CURVE_DISTANCE),
    ], 
    []
  );

  const sceneOpacity = useRef(0);
  const lineMaterialRef = useRef();

  const curve = useMemo(() => {
    return new THREE.CatmullRomCurve3(curvePoints, false, "catmullrom", 0.5);
  }, []);

  const islands = useMemo(
    () => [
      // STARTING
      {
        position: new Vector3(-3.5, -3.2, -7),
      },
      {
        position: new Vector3(3.5, -4, -10),
      },
      {
        scale: new Vector3(4, 4, 4),
        position: new Vector3(-18, 0.2, -68),
        // rotation: new Euler(-Math.PI / 5, Math.PI / 6, 0),
      },
      {
        scale: new Vector3(2.5, 2.5, 2.5),
        position: new Vector3(10, -1.2, -52),
      },
      // FIRST POINT
      {
        scale: new Vector3(4, 4, 4),
        position: new Vector3(
          curvePoints[1].x + 10,
          curvePoints[1].y - 4,
          curvePoints[1].z + 64
        ),
      },
      {
        scale: new Vector3(3, 3, 3),
        position: new Vector3(
          curvePoints[1].x - 20,
          curvePoints[1].y + 4,
          curvePoints[1].z + 28
        ),
        // rotation: new Euler(0, Math.PI / 7, 0),
      },
      {
        // rotation: new Euler(0, Math.PI / 7, Math.PI / 5),
        scale: new Vector3(5, 5, 5),
        position: new Vector3(
          curvePoints[1].x - 13,
          curvePoints[1].y + 4,
          curvePoints[1].z - 62
        ),
      },
      {
        // rotation: new Euler(Math.PI / 2, Math.PI / 2, Math.PI / 3),
        scale: new Vector3(5, 5, 5),
        position: new Vector3(
          curvePoints[1].x + 54,
          curvePoints[1].y + 2,
          curvePoints[1].z - 82
        ),
      },
      {
        scale: new Vector3(5, 5, 5),
        position: new Vector3(
          curvePoints[1].x + 8,
          curvePoints[1].y - 14,
          curvePoints[1].z - 22
        ),
      },
      // SECOND POINT
      {
        scale: new Vector3(3, 3, 3),
        position: new Vector3(
          curvePoints[2].x + 6,
          curvePoints[2].y - 7,
          curvePoints[2].z + 50
        ),
      },
      {
        scale: new Vector3(2, 2, 2),
        position: new Vector3(
          curvePoints[2].x - 2,
          curvePoints[2].y + 4,
          curvePoints[2].z - 26
        ),
      },
      {
        scale: new Vector3(4, 4, 4),
        position: new Vector3(
          curvePoints[2].x + 12,
          curvePoints[2].y + 1,
          curvePoints[2].z - 86
        ),
        // rotation: new Euler(Math.PI / 4, 0, Math.PI / 3),
      },
      // THIRD POINT
      {
        scale: new Vector3(3, 3, 3),
        position: new Vector3(
          curvePoints[3].x + 3,
          curvePoints[3].y - 10,
          curvePoints[3].z + 50
        ),
      },
      {
        scale: new Vector3(3, 3, 3),
        position: new Vector3(
          curvePoints[3].x - 10,
          curvePoints[3].y,
          curvePoints[3].z + 30
        ),
        // rotation: new Euler(Math.PI / 4, 0, Math.PI / 5),
      },
      {
        scale: new Vector3(4, 4, 4),
        position: new Vector3(
          curvePoints[3].x - 20,
          curvePoints[3].y - 5,
          curvePoints[3].z - 8
        ),
        // rotation: new Euler(Math.PI, 0, Math.PI / 5),
      },
      {
        scale: new Vector3(5, 5, 5),
        position: new Vector3(
          curvePoints[3].x + 0,
          curvePoints[3].y - 5,
          curvePoints[3].z - 98
        ),
        // rotation: new Euler(0, Math.PI / 3, 0),
      },
      // FOURTH POINT
      {
        scale: new Vector3(3, 3, 3),
        position: new Vector3(
          curvePoints[4].x,
          curvePoints[4].y,
          curvePoints[4].z
        ),
      },
      {
        scale: new Vector3(3, 3, 3),
        position: new Vector3(
          curvePoints[4].x + 24,
          curvePoints[4].y - 6,
          curvePoints[4].z - 42
        ),
        // rotation: new Euler(Math.PI / 4, 0, Math.PI / 5),
      },
      {
        scale: new Vector3(3, 3, 3),
        position: new Vector3(
          curvePoints[4].x - 4,
          curvePoints[4].y + 9,
          curvePoints[4].z - 62
        ),
        // rotation: new Euler(Math.PI / 3, 0, Math.PI / 3),
      },
      // FINAL
      {
        scale: new Vector3(3, 3, 3),
        position: new Vector3(
          curvePoints[7].x + 12,
          curvePoints[7].y - 5,
          curvePoints[7].z + 60
        ),
        // rotation: new Euler(-Math.PI / 4, -Math.PI / 6, 0),
      },
      {
        scale: new Vector3(3, 3, 3),
        position: new Vector3(
          curvePoints[7].x - 12,
          curvePoints[7].y + 5,
          curvePoints[7].z + 120
        ),
        // rotation: new Euler(Math.PI / 4, Math.PI / 6, 0),
      },
    ],
    []
  );

  const shape = useMemo(() => {
    const shape = new THREE.Shape();
    shape.moveTo(0, -0.08);
    shape.lineTo(0, 0.08);

    return shape;
  }, [curve]);

  const cameraGroup = useRef();
  const cameraRail = useRef();
  const camera = useRef();
  const scroll = useScroll();
  const lastScroll = useRef(0);

  const { play, setHasScroll, end, setEnd } = usePlay();

  useFrame((_state, delta) => {

    // 반응형 페이지
    if (window.innerWidth > window.innerHeight) {
      // LANDSCAPE
      camera.current.fov = 30;
      camera.current.position.z = 5;
    } else {
      // PORTRAIT
      camera.current.fov = 80;
      camera.current.position.z = 2;
    }
    
    // 스크롤
    if (lastScroll.current <= 0 && scroll.offset > 0) {
      setHasScroll(true);
    }

    if (play && !end && sceneOpacity.current < 1) {
      sceneOpacity.current = THREE.MathUtils.lerp(
        sceneOpacity.current,
        1,
        delta * 0.1
      )
    }

    if (end && sceneOpacity.current > 0) {
      sceneOpacity.current = THREE.MathUtils.lerp(
        sceneOpacity.current,
        0,
        delta
      );
    }

    lineMaterialRef.current.opacity = sceneOpacity.current;

    if (end) {
      return;
    }

    const scrollOffset =  Math.max(0, scroll.offset);

    let friction = 1;
    let resetCameraRail = true;

    // textSections.forEach((textSection) => {
    //   const distance = textSection.position.distanceTo(
    //     cameraGroup.current.position
    //   );

    //   if (distance < FRICTION_DISTANCE) {
    //     friction = Math.max(distance / FRICTION_DISTANCE, 0.1);
    //     const targetCameraQuaternion = new Vector3(
    //       (1 - distance / FRICTION_DISTANCE) * textSection.cameraRailDist,
    //       0,
    //       0,
    //     );
    //     cameraRail.current.position.lerp(targetCameraQuaternion, delta);
    //     resetCameraRail = false;
    //   }
    // });
    // if (resetCameraRail) {
    //   const targetCameraRailPosition = new Vector3(0, 0, 0);
    //   cameraRail.current.position.lerp(targetCameraRailPosition, delta);
    // }

    let lerpedScrollOffset = THREE.MathUtils.lerp(
      lastScroll.current,
      scrollOffset, 
      delta*friction
    );

    lerpedScrollOffset = Math.min(lerpedScrollOffset, 1);
    lerpedScrollOffset = Math.max(lerpedScrollOffset, 0);

    lastScroll.current = lerpedScrollOffset;
    tl.current.seek(lerpedScrollOffset * tl.current.duration());

    const curPoint = curve.getPoint(scrollOffset);

    cameraGroup.current.position.lerp(curPoint, delta * 24);

    const lookAtPoint = curve.getPoint(
      Math.min(scrollOffset + CURVE_AHEAD_CAMERA, 1)
    );

    const currentLookAt = cameraGroup.current.getWorldDirection(
      new THREE.Vector3()
    );

    const targetLookAt = new THREE.Vector3()
      .subVectors(curPoint, lookAtPoint)
      .normalize();

    const lookAt = currentLookAt.lerp(targetLookAt, delta * 24);
    cameraGroup.current.lookAt(
      cameraGroup.current.position.clone().add(lookAt)
    );

    // Ship rotation

    const tangent = curve.getTangent(scrollOffset + CURVE_AHEAD_SHIP);

    const nonLerpLookAt = new Group();
    nonLerpLookAt.position.copy(curPoint);
    nonLerpLookAt.lookAt(nonLerpLookAt.position.clone().add(targetLookAt));

    tangent.applyAxisAngle(
      new THREE.Vector3(0, 1, 0),
      -nonLerpLookAt.rotation.y
    );

    let angle = Math.atan2(-tangent.z, tangent.x);
    angle = -Math.PI / 2 + angle;

    let angleDegrees = (angle * 180) / Math.PI;
    angleDegrees *= 0.5;

    if (angleDegrees < 0) {
      angleDegrees = Math.max(angleDegrees, -SHIP_MAX_ANGLE);
    }
    if (angleDegrees < 0) {
      angleDegrees = Math.min(angleDegrees, SHIP_MAX_ANGLE);
    }

    angle = (angleDegrees * Math.PI) / 180;

    const targetShipQuaternion = new THREE.Quaternion().setFromEuler(
      new THREE.Euler(
        ship.current.rotation.x,
        ship.current.rotation.y,
        angle
      )
    );

    ship.current.quaternion.slerp(targetShipQuaternion, delta * 2);

    if (
      cameraGroup.current.position.z <
      curvePoints[curvePoints.length - 1].z + 100
    ) {
      setEnd(true);
      shipOutTl.current.play();
    }
  });

  const ship = useRef();

  const tl = useRef();
  const backgroundColors = useRef({
    colorA: "#84D1FE",
    colorB: "#ffffff",
  })

  const shipInTl = useRef();
  const shipOutTl = useRef();

  useLayoutEffect(() => {
    tl.current = gsap.timeline();

    tl.current.to(backgroundColors.current, {
      duration: 1,
      colorA: "#84D1FE",
      colorB: "#ffffff",
    });
    tl.current.to(backgroundColors.current, {
      duration: 1,
      colorA: "#84D1FE",
      colorB: "#ffffff",
    });
    tl.current.to(backgroundColors.current, {
      duration: 1,
      colorA: "#84D1FE",
      colorB: "#ffffff",
    });

    tl.current.pause();

    shipInTl.current = gsap.timeline();
    shipInTl.current.pause();
    shipInTl.current.from(ship.current.position, {
      duration: 3,
      z: 5,
      // y: -2,
    });

    shipOutTl.current = gsap.timeline();
    shipOutTl.current.pause();

    shipOutTl.current.to(
      ship.current.position,
      {
        duration: 20,
        z: -250,
        y: -1,
      },
      0
    );

    shipOutTl.current.to(
      cameraRail.current.position,
      {
        duration: 10,
        y: -1,
      },
      0
    );

    shipOutTl.current.to(ship.current.position, {
      duration: 1,
      z: -1000,
      y: -2
    });
  }, []);

  useEffect(() => {
    if (play) {
      shipInTl.current.play();
    }
  }, [play]);

  const navigate = useRouter();

  // 리뷰
  const handleIslandClick1 = () => {
    navigate.push('/review')
  };

  // 경매 
  const handleIslandClick2 = () => {
    navigate.push('/AuctionLivePage')
  };

  // 작가
  const handleIslandClick3 = () => {
    navigate.push('/ArtistHomePage')
  }

  // const handleIslandClick4 = () => {
  //   navigate.push('/ArtistHomePage')
  // }

  return useMemo(() => (
    <>
      {/* <OrbitControls /> */}
      <directionalLight position={[0, 3, 1]} intensity={0.1} />
      <group ref={cameraGroup}>
        <Background backgroundColors={backgroundColors}/>
        <group ref={cameraRail}>
          <PerspectiveCamera 
            ref={camera}
            position={[0, 0, 5]} 
            fov={30}
            makeDefault 
          />
        </group>
        <group ref={ship}>
          <Float floatIntensity={1} speed={1.5} rotationIntensity={0.5}>
            <Ship rotation-y={Math.PI * 1.5} scale={[0.015, 0.015, 0.015]}
            position-y={-1.3} />
          </Float>
        </group>
      </group>

      {/* LINE */}
      <group position-y={-2}>
        <mesh>
          <extrudeGeometry
              args={[
                shape,
                {
                  steps: LINE_NB_POINTS,
                  bevelEnabled: false,
                  extrudePath: curve,
                },
              ]}
            />
            <meshStandardMaterial 
              color={"white"}
              ref={lineMaterialRef}
              opacity={0.5}
              transparent
              envMapIntensity={2}
            />
        </mesh>
      </group>

      {/* CLOUDS */}
      {/* {islands.map((island, index) => (
        <mesh onClick={() => handleIslandClick()}>
          <Island_1 sceneOpacity={sceneOpacity} {...island} key={index} 
          scale={[0.01, 0.01, 0.01]} position-y={-2}/>
        </mesh>
      ))} */}

      {/* Island 1 */}
      <mesh onClick={() => handleIslandClick1()}>
        <Island_1 sceneOpacity={sceneOpacity} position={islands[8].position}
        scale={[0.075, 0.075, 0.075]} position-y={-4} position-x={-6.5}/>
      </mesh>

      <mesh onClick={() => handleIslandClick1()}>
        <Review_Text sceneOpacity={sceneOpacity} position={islands[8].position}
        scale={[1, 1, 1]} position-y={2} position-x={-1}/>
      </mesh>

      {/* Island 2 */}
      <mesh onClick={() => handleIslandClick2()}>
        <Island_2 sceneOpacity={sceneOpacity} position={islands[9].position}
        scale={[0.075, 0.075, 0.075]} position-y={-4} position-x={110}/>
      </mesh>

      <mesh onClick={() => handleIslandClick2()}>
        <Auction_Text sceneOpacity={sceneOpacity} position={islands[9].position}
        scale={[1, 1, 1]} position-y={2} position-x={102} rotation-y={-0.5}/>
      </mesh>

      {/* Island 3 */}
      <mesh onClick={() => handleIslandClick3()}>
        <Island_3 sceneOpacity={sceneOpacity} position={islands[12].position}
        scale={[0.15, 0.15, 0.15]} position-y={-2.5} position-x={-70}/>
      </mesh>

      <mesh onClick={() => handleIslandClick3()}>
        <Artist_Text sceneOpacity={sceneOpacity} position={islands[12].position}
        scale={[1, 1, 1]} position-y={2} position-x={-75} rotation-y={0.7} />
      </mesh>

      {/* Island 4 */}
      <mesh onClick={() => handleIslandClick3()}>
        <Island_4 sceneOpacity={sceneOpacity} position={islands[17].position}
        scale={[1.5, 1.5, 1.5]} position-y={-3} position-x={90}/>
      </mesh>

      <mesh onClick={() => handleIslandClick3()}>
        <Rock_1 sceneOpacity={sceneOpacity} position={islands[1].position}
        scale={[0.5, 0.5, 0.5]} position-y={-3}/>
      </mesh>
    </>
  ),
  []);
};

export default Experience;