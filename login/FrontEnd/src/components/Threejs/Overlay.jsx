'use client'
import { useProgress } from "@react-three/drei";
import { usePlay } from "../Threejs/contexts/PlayProvider";

const Overlay = () => {
    const { progress } = useProgress();
    const { play, end, setPlay, hasScroll } = usePlay();

    return (
        <div className={`overlay ${play ? "overlay--disable" : ""}
        ${hasScroll ? "overlay--scrolled" : ""}`}>
            <div className={`loader ${progress === 100 ? "loader--disappear": ""}`} />
            {progress === 100 && (
            <div className={`intro ${play ? "intro--disappear" : ""}`}>
                <h1 className="logo1">
                    Bon Voyage
                </h1>
                <p className="intro__scroll">Scroll to begin the journey</p>
                <button 
                    className="explore" 
                    onClick = {() => {
                        setPlay(true);
                }}>
                    Explore
                </button>
            </div>
            )}
            <div className={`outro ${end ? "outro--appear" : ""}`}>
                <p className="outro__text">
                </p>
                <button 
                    className="restart_btn"
                    onClick={() => {
                        window.location.reload();
                    }}>
                    Restart
                </button>
            </div>
        </div>
    )
}

export default Overlay;