'use client'
import React, { createContext, useContext, useState, ReactNode } from "react";

// Define the types for the context
interface PlayContextType {
  play: boolean;
  setPlay: React.Dispatch<React.SetStateAction<boolean>>;
  end: boolean;
  setEnd: React.Dispatch<React.SetStateAction<boolean>>;
  hasScroll: boolean;
  setHasScroll: React.Dispatch<React.SetStateAction<boolean>>;
}

// Create the context
const Context = createContext<PlayContextType | undefined>(undefined);

// Define the props for PlayProvider
interface PlayProviderProps {
  children: ReactNode;
}

// Create the PlayProvider component
const PlayProvider: React.FC<PlayProviderProps> = ({ children }) => {
  const [play, setPlay] = useState(false);
  const [end, setEnd] = useState(false);
  const [hasScroll, setHasScroll] = useState(false);

  const contextValue: PlayContextType = {
    play,
    setPlay,
    end,
    setEnd,
    hasScroll,
    setHasScroll,
  };

  return <Context.Provider value={contextValue}>{children}</Context.Provider>;
};

// Create the usePlay hook
const usePlay = (): PlayContextType => {
  const context = useContext(Context);

  if (context === undefined) {
    throw new Error("usePlay must be used within a PlayProvider");
  }

  return context;
};

// Export the components and hook
export { PlayProvider, usePlay };
