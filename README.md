# symbolic-computation

####Пример входных и выходных данных:

Вход: a+3  
Выход:

    <sum>
      <variable>
          <name>a</name>
      </variable>
      <number>
          <data>3.0</data>
      </number>
    </sum>

(a+b)*c

    <sum>
      <mul>
          <variable>
              <name>a</name>
          </variable>
          <variable>
              <name>c</name>
          </variable>
      </mul>
      <mul>
          <variable>
              <name>b</name>
          </variable>
          <variable>
              <name>c</name>
          </variable>
      </mul>
    </sum>

a^2*a

    <power>
      <variable>
          <name>a</name>
      </variable>
      <number>
          <data>3.0</data>
      </number>
    </power>

f(x,y)=x*y   
f(3,4) 

      <number>
          <data>12.0</data>
      </number>
      
func(f)=a^2+b*2;a=first(f);b=last(f)      
func(a+b+c)

      <sum>
          <power>
              <variable>
                  <name>a</name>
              </variable>
              <number>
                  <data>2.0</data>
              </number>
          </power>
          <mul>
              <variable>
                  <name>c</name>
              </variable>
              <number>
                  <data>2.0</data>
              </number>
          </mul>
      </sum>
