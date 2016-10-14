function fact(n){
if(n==0.0)return 1.0;else return n*fact(n-1.0);}
function main(){
print_string("fact(5)=");
print_number(fact(5.0));
}
