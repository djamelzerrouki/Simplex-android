# Simplex Android
## Linear programming and simplex algorithm
A linear programming problem is a mathematical problem of optimization of a linear function under constraints of affine inequalities. More precisely, it is a question of determining the maximum of a function of the type

 	                  a1*x1+⋯+an*xn
where the variables x1,…,xn verify type inequalities

                   	ci,1x1+⋯+ci,nxn≤bi,	
for 1 ≤ i ≤p. Such problems frequently occur in economics.

There are several algorithms to solve such problems. The best known is the simplex algorithm. We propose to study its operation on an example. We have soft variables, x, y and we want to maximize the quantity
## Example 
Solve using the Simplex method the following problem:

 	Maximize	Z = f(x,y) = 3x + 2y
 	subject to:	 2x + y ≤ 18
 	                 2x + 3y ≤ 42
 	                 3x + y ≤ 24
 	                 x ≥ 0 , y ≥ 0


<div style="text-align: center">
    <table>
        <tr>
            <td style="text-align: center">
                     <img src="https://github.com/djamelzerrouki/Simplex-android/blob/master/image/Screenshot1.png"  />
           </td>            
            <td style="text-align: center">   
                       <img src="https://github.com/djamelzerrouki/Simplex-android/blob/master/image/Screenshot2.png"  />
            </td>
            <td style="text-align: center">
                      <img src="https://github.com/djamelzerrouki/Simplex-android/blob/master/image/Screenshot3.png"  />
           </td>
      </tr>
      <tr>
            <td style="text-align: center">
                      <img src="https://github.com/djamelzerrouki/Simplex-android/blob/master/image/Screenshot4.png"  />
            </td>    
            </td>
            <td style="text-align: center">
                      <img src="https://github.com/djamelzerrouki/Simplex-android/blob/master/image/Screenshot5.png"  />
            </td>
            <td style="text-align: center">
                      <img src="https://github.com/djamelzerrouki/Simplex-android/blob/master/image/Screenshot6.png"  "/>
            </td> 
      </tr>
      
      
  </table>
  </div>
