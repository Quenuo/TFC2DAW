import{c as g}from"./chunk-AQGG5LPX.js";import{Gb as m,J as i,M as u,R as s,h as l,l as n,w as c}from"./chunk-SV6CTXGA.js";var I=(()=>{class a{constructor(e,o){this.http=e,this.router=o,this.apiUrl="https://jurasickparkmanagement.onrender.com/auth",this.currentUserSubject=new l(null),this.currentUser$=this.currentUserSubject.asObservable(),this.banInfoKey="banInfo";let r=localStorage.getItem("user");if(r)try{this.currentUserSubject.next(JSON.parse(r))}catch(t){console.error("Error parsing user data from localStorage:",t),localStorage.removeItem("user"),localStorage.removeItem("token")}}login(e,o){return this.http.post(`${this.apiUrl}/login`,{email:e,password:o}).pipe(i(r=>{let t=r.user;if(t.banned){localStorage.setItem("banInfo",JSON.stringify({reason:t.banReason,bannedUntil:t.banExpirationDate,email:t.email})),this.router.navigate(["/banned"]);return}localStorage.setItem("token",r.token),localStorage.setItem("user",JSON.stringify(r.user)),this.currentUserSubject.next(r.user)}))}register(e,o,r){return this.http.post(`${this.apiUrl}/register`,{email:e,name:o,password:r}).pipe(i(t=>{localStorage.setItem("token",t.token),localStorage.setItem("user",JSON.stringify(t.user)),this.currentUserSubject.next(t.user),this.router.navigate(["/setup"])}),c(this.handleError))}logout(){localStorage.removeItem("token"),localStorage.removeItem("user"),localStorage.removeItem("click"),localStorage.removeItem("level"),this.currentUserSubject.next(null),this.router.navigate(["/"])}getBanInfo(){let e=localStorage.getItem(this.banInfoKey);return e?JSON.parse(e):null}handleError(e){return e.error&&e.error.message?n(()=>new Error(e.error.message)):n(()=>new Error("Ocurri\xF3 un error inesperado. Por favor, int\xE9ntelo de nuevo."))}isAuthenticated(){return!!localStorage.getItem("token")}static{this.\u0275fac=function(o){return new(o||a)(s(m),s(g))}}static{this.\u0275prov=u({token:a,factory:a.\u0275fac,providedIn:"root"})}}return a})();export{I as a};
