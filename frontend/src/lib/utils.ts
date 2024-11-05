import { jwtDecode, JwtPayload } from "jwt-decode";

export function validToken(): boolean {
    const jvToken = localStorage.getItem("jvToken");

    if (!jvToken) {
        return false;
    }

    if (!jvToken.includes('.')) {
        console.warn("Invalid token format - Token:", jvToken);
        localStorage.removeItem("jvToken");
        return false;
    }

    let decodedToken: JwtPayload;

    try {
        decodedToken = jwtDecode<JwtPayload>(jvToken);
    } catch (error) {
        console.error("Token decode error:", error);
        localStorage.removeItem("jvToken");
        return false;
    }

    if (!decodedToken.exp) {
        console.warn("Token missing expiration");
        localStorage.removeItem("jvToken");
        return false;
    }

    if ((decodedToken.exp * 1000) <= Date.now()) {
        console.warn("Token expired");
        localStorage.removeItem("jvToken");
        return false;
    }

    return true;
}

export function isValidTokenFormat(token: string | null): boolean {
    if (!token) {
        return false;
    }
    return token.split('.').length === 3;
}


export const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])(?!^\s)(?!\s$).{8,}$/;