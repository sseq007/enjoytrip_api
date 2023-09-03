import { listSido } from "@/api/area.js";

const areaStore = {
    namespaced: true,
    state: {
        sidos: [{ value: null, text: "지역 선택 안 함" }],
    },
    getters: {},
    mutations: {
        CLEAR_SIDO_LIST(state) {
            state.sidos = [{ value: null, text: "지역 선택 안 함" }];
        },
        SET_SIDO_LIST(state, sidos) {
            sidos.forEach((sido) => {
                console.log(sido.sido_code);
                console.log(sido.sido_name);
                state.sidos.push({ value: sido.sido_code, text: sido.sido_name });
            });
        },
    },
    actions: {
        getSido: ({ commit }) => {
            listSido(
                ({ data }) => {
                    commit("SET_SIDO_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
    },
};

export default areaStore;