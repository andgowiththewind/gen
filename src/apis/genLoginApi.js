import request from '@/utils/request'

export function signInOrSignUpFn(data) {
    return request({
        url: '/login/signInOrSignUp',
        method: 'post',
        data: data,
        headers: {
            enableAntiShake: true
        }
    })
}