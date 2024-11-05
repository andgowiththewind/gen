// src/store/modules/auth.js

const state = {
    token: null
};

const mutations = {
    SET_TOKEN(state, token) {
        state.token = token;
    }
};

const actions = {
    saveToken({commit}, token) {
        commit('SET_TOKEN', token);
    }
};

const getters = {
    getToken(state) {
        return state.token;
    }
};

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters
};
