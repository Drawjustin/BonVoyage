import { createSlice } from "@reduxjs/toolkit";
import LocalStorage from '@/constants'

const initialState = LocalStorage.getItem('user') ?
    JSON.parse(LocalStorage.getItem('user')) : { email: "", token: "", id: "" }


export const artistsSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {
        setUser: (state, action) => {
            state.email = action.payload.email;
            state.token = action.payload.token;
            state.id = action.payload.id;

            LocalStorage.setItem('user', JSON.stringify(state));
        },
        removeUser: (state) => {
            state.email = "";
            state.token = "";
            state.id = "";

            LocalStorage.setItem('user', JSON.stringify(state));
        }
    }
})

export const { setUser, removeUser } = artistsSlice.actions;
export default artistsSlice.reducer;