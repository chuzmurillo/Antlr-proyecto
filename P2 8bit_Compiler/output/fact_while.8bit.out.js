function fact(n){
let {f=1.0;}while(n>0.0){f=f*n;n=n-1.0;}return f;}
function main(){
print_string("fact(5)=");
print_number(fact(5.0));
}
