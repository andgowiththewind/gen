import request from '@/utils/request'

export function signInOrSignUpFn(data) {
    return request({
        url: '/login/test',
        method: 'post',
        data: data,
        headers: {
            enableAntiShake: true
        }
    })
}