class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n == 0){
            return true;

        }
        for(int i =0;i<flowerbed.length;i++){
            if(flowerbed[i] == 0 && (i == 0 || flowerbed[i-1] == 0) && (i == flowerbed.length-1 || flowerbed[i+1] == 0)){
                flowerbed [i] = 1;
                n--;

                if(n == 0){
                    return true;
                }
            }
        }
        return false;
    }            
    
}




// "Main array ko ek baar traverse karta hoon. Har empty plot ke liye check karta hoon ki uske left aur right me flower na ho (ya boundary ho).
// Agar safe hai to flower plant karta hoon aur n ko decrement karta hoon. 
// Agar n zero ho jata hai to true return kar deta hoon, warna loop ke baad false."
