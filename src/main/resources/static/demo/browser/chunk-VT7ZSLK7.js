import{a as C}from"./chunk-LPHYOPSK.js";import{c as v}from"./chunk-AQGG5LPX.js";import{a as x,b as p,c as _,d as P,e as S,g as N,h as y,l as M,m as F,n as O}from"./chunk-D2BLZIV3.js";import{Bb as h,Ha as u,Ka as c,Na as g,Qa as r,Ra as n,Sa as f,Ua as b,W as s,Wa as o,_a as k,wa as d,xa as l}from"./chunk-SV6CTXGA.js";function E(i,w){i&1&&(r(0,"span",7),o(1,"Park name is required"),n())}var I=(()=>{class i{constructor(a,e,t){this.fb=a,this.parkService=e,this.router=t,this.parkForm=this.fb.group({parkName:["",[p.required,p.minLength(3),p.maxLength(50)]]})}onSubmit(){if(this.parkForm.valid){let{parkName:a}=this.parkForm.value;this.parkService.createParkName(a).subscribe({next:()=>{this.router.navigate(["/park"])},error:e=>{console.error("Failed to set park name:",e)}})}}static{this.\u0275fac=function(e){return new(e||i)(l(F),l(C),l(v))}}static{this.\u0275cmp=s({type:i,selectors:[["create-park"]],standalone:!0,features:[k],decls:14,vars:3,consts:[[1,"naming-container"],[1,"naming-card"],[1,"subtitle"],[3,"ngSubmit","formGroup"],[1,"form-group"],["for","parkName"],["type","text","id","parkName","formControlName","parkName","placeholder","Enter your park name","maxlength","50"],[1,"error"],["type","submit",3,"disabled"]],template:function(e,t){if(e&1&&(r(0,"div",0)(1,"div",1)(2,"h2"),o(3,"Name Your Jurassic Park"),n(),r(4,"p",2),o(5,"Before your adventure begins, give your park a unique name!"),n(),r(6,"form",3),b("ngSubmit",function(){return t.onSubmit()}),r(7,"div",4)(8,"label",5),o(9,"Park Name"),n(),f(10,"input",6),u(11,E,2,0,"span",7),n(),r(12,"button",8),o(13,"Start Your Adventure"),n()()()()),e&2){let m;d(6),c("formGroup",t.parkForm),d(5),g((m=t.parkForm.get("parkName"))!=null&&m.touched&&((m=t.parkForm.get("parkName"))!=null&&m.invalid)?11:-1),d(),c("disabled",t.parkForm.invalid)}},dependencies:[h,O,S,x,_,P,M,N,y],styles:[".naming-container[_ngcontent-%COMP%]{height:100vh;display:flex;align-items:center;justify-content:center;background:linear-gradient(135deg,#1a472a,#2a623d)}.naming-card[_ngcontent-%COMP%]{background:#fff;padding:2rem;border-radius:8px;box-shadow:0 4px 6px #0000001a;width:100%;max-width:400px}h2[_ngcontent-%COMP%]{text-align:center;color:#1a472a;margin-bottom:1rem}.subtitle[_ngcontent-%COMP%]{text-align:center;color:#666;margin-bottom:2rem}.form-group[_ngcontent-%COMP%]{margin-bottom:1.5rem}label[_ngcontent-%COMP%]{display:block;margin-bottom:.5rem;color:#333}input[_ngcontent-%COMP%]{width:100%;padding:.75rem;border:1px solid #ddd;border-radius:4px;font-size:1rem}.error[_ngcontent-%COMP%]{color:#e74c3c;font-size:.875rem;margin-top:.25rem;display:block}button[_ngcontent-%COMP%]{width:100%;padding:.75rem;background:#2a623d;color:#fff;border:none;border-radius:4px;cursor:pointer;font-size:1rem}button[_ngcontent-%COMP%]:disabled{background:#ccc}"]})}}return i})();export{I as CreateParkComponent};