import{a as k}from"./chunk-LPHYOPSK.js";import{a as y}from"./chunk-HZU4245N.js";import{c as P,d as F}from"./chunk-AQGG5LPX.js";import{a as O,b as m,c as R,d as I,e as N,g as L,h as T,m as q,n as j}from"./chunk-D2BLZIV3.js";import{Bb as M,Ha as d,Ka as b,Na as c,Qa as n,Ra as i,Sa as p,Ua as E,Va as u,W as C,Wa as a,Xa as f,Ya as w,_a as S,wa as s,xa as g}from"./chunk-SV6CTXGA.js";function V(t,v){if(t&1&&(n(0,"div")(1,"small",14),a(2),i()()),t&2){let e=u();s(2),f(e.getErrorMessage("email"))}}function A(t,v){if(t&1&&(n(0,"div")(1,"small",14),a(2),i()()),t&2){let e=u();s(2),f(e.getErrorMessage("name"))}}function D(t,v){if(t&1&&(n(0,"div")(1,"small",14),a(2),i()()),t&2){let e=u();s(2),f(e.getErrorMessage("password"))}}function G(t,v){if(t&1&&(n(0,"div")(1,"small",14),a(2),i()()),t&2){let e=u();s(2),f(e.getErrorMessage("confirmPassword"))}}function z(t,v){if(t&1&&(n(0,"div")(1,"small",15),a(2),i()()),t&2){let e=u();s(2),w(" ",e.errorMessage,"")}}var W=(()=>{class t{constructor(e,o,r,l){this.fb=e,this.authService=o,this.router=r,this.parkService=l,this.errorMessage=null,this.registerForm=this.fb.group({email:["",[m.required,m.email]],name:["",[m.required]],password:["",[m.required,m.minLength(6)]],confirmPassword:["",[m.required]]},{validators:this.passwordMatchValidator})}passwordMatchValidator(e){return e.get("password")?.value===e.get("confirmPassword")?.value?null:{mismatch:!0}}getErrorMessage(e){let o=this.registerForm.get(e);return o?.hasError("required")?"Este campo es obligatorio":o?.hasError("email")?"Introduce un correo electr\xF3nico v\xE1lido":o?.hasError("name")?"El campo nombre no debe de estar vacio":o?.hasError("minlength")?`La contrase\xF1a debe tener al menos ${o.errors?.minlength.requiredLength} caracteres`:e==="confirmPassword"&&this.registerForm.hasError("mismatch")?"Las contrase\xF1as no coinciden":""}onSubmit(){if(this.registerForm.valid){let{email:e,name:o,password:r}=this.registerForm.value;this.authService.register(e,o,r).subscribe({next:()=>{this.authService.login(e,r).subscribe({next:()=>{this.parkService.getParkStatus().subscribe({next:l=>{l.name?this.router.navigate(["/park"]):this.router.navigate(["/setup"])},error:()=>{this.router.navigate(["/setup"])}})},error:l=>{this.errorMessage="Error de inicio de sesi\xF3n despu\xE9s del registro."}})},error:l=>{this.errorMessage=l}})}}static{this.\u0275fac=function(o){return new(o||t)(g(q),g(y),g(P),g(k))}}static{this.\u0275cmp=C({type:t,selectors:[["register"]],standalone:!0,features:[S],decls:32,vars:7,consts:[[1,"register-container"],[1,"register-card"],[3,"ngSubmit","formGroup"],[1,"form-group"],["for","email"],["type","email","id","email","formControlName","email","placeholder","Enter your email"],["for","name"],["type","text","name","name","id","name","formControlName","name","placeholder","Enter your name"],["for","password"],["type","password","id","password","formControlName","password","placeholder","Enter your password"],["for","confirmPassword"],["type","password","id","confirmPassword","formControlName","confirmPassword","placeholder","Confirm your password"],["type","submit",3,"disabled"],["routerLink","/"],[1,"error"],[1,"error-message"]],template:function(o,r){if(o&1&&(n(0,"div",0)(1,"div",1)(2,"h2"),a(3,"Create Your Park Account"),i(),n(4,"form",2),E("ngSubmit",function(){return r.onSubmit()}),n(5,"div",3)(6,"label",4),a(7,"Email"),i(),p(8,"input",5),d(9,V,3,1,"div"),i(),n(10,"label",6),a(11,"Name"),i(),n(12,"div",3),p(13,"input",7),d(14,A,3,1,"div"),i(),n(15,"div",3)(16,"label",8),a(17,"Password"),i(),p(18,"input",9),d(19,D,3,1,"div"),i(),n(20,"div",3)(21,"label",10),a(22,"Confirm Password"),i(),p(23,"input",11),d(24,G,3,1,"div"),i(),d(25,z,3,1,"div"),n(26,"button",12),a(27,"Register"),i()(),n(28,"p"),a(29," Already have an account? "),n(30,"a",13),a(31,"Login here"),i()()()()),o&2){let l,h,x,_;s(4),b("formGroup",r.registerForm),s(5),c((l=r.registerForm.get("email"))!=null&&l.touched&&((l=r.registerForm.get("email"))!=null&&l.invalid)?9:-1),s(5),c((h=r.registerForm.get("name"))!=null&&h.touched&&((h=r.registerForm.get("name"))!=null&&h.invalid)?14:-1),s(5),c((x=r.registerForm.get("password"))!=null&&x.touched&&((x=r.registerForm.get("password"))!=null&&x.invalid)?19:-1),s(5),c((_=r.registerForm.get("confirmPassword"))!=null&&_.touched&&((_=r.registerForm.get("confirmPassword"))!=null&&_.invalid)?24:-1),s(),c(r.errorMessage?25:-1),s(),b("disabled",r.registerForm.invalid)}},dependencies:[M,j,N,O,R,I,L,T,F],styles:[".register-container[_ngcontent-%COMP%]{height:100vh;display:flex;align-items:center;justify-content:center;background:linear-gradient(135deg,#1a472a,#2a623d)}.register-card[_ngcontent-%COMP%]{background:#fff;padding:2rem;border-radius:8px;box-shadow:0 4px 6px #0000001a;width:100%;max-width:400px}h2[_ngcontent-%COMP%]{text-align:center;color:#1a472a;margin-bottom:2rem}.form-group[_ngcontent-%COMP%]{margin-bottom:1rem}label[_ngcontent-%COMP%]{display:block;margin-bottom:.5rem;color:#333}input[_ngcontent-%COMP%]{width:100%;padding:.75rem;border:1px solid #ddd;border-radius:4px;margin-bottom:1rem}button[_ngcontent-%COMP%]{width:100%;padding:.75rem;background:#2a623d;color:#fff;border:none;border-radius:4px;cursor:pointer;font-size:1rem}button[_ngcontent-%COMP%]:disabled{background:#ccc}p[_ngcontent-%COMP%]{text-align:center;margin-top:1rem}a[_ngcontent-%COMP%]{color:#2a623d;text-decoration:none}.error[_ngcontent-%COMP%]{color:red;font-size:.875rem}.error-message[_ngcontent-%COMP%]{color:red;font-size:1rem;margin-top:1rem}"]})}}return t})();export{W as RegisterComponent};
