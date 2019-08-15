{-
Troy Dean - 0222566
-}
{-
1.
-}
halve:: [a] -> ([a], [a])
halve [] = ([], [])
halve [_] = ([], [])
halve (x) = splitAt (((length x) + 1) `div` 2) x

{- 
2.
-}
meld:: [Int] -> [Int] -> [Int]
meld [][] = []
meld [] x = x
meld x [] = x
meld (x:xs) (y:ys) 
    | y < x = y: meld (x:xs) ys
    | otherwise = x: meld xs (y:ys)

{-
3.
-}
integerSort:: [Int] -> [Int]
integerSort [] = []
integerSort [x] = [x]
integerSort (x) = [z | (a,b) <- [halve x],   z <- meld (integerSort b) (integerSort a)]

{-
4.
-}
replicate:: Int -> a -> [a]
replicate n a = [a| _ <- [1..n]]

{-
5.

-}

{-
6.
-}
d2i:: [Int] -> Int
d2i x = foldl ((+).(*10)) 0 x
{-
7. i) Mixing of types char and num in the list
   ii) Expression did type-check. Returned [(*), 0, (+)] :: (Num a, Num (a -> a -> a)) => [a -> a -> a]
   iii) First tuple is (num, bool) second is(char,bool) so the elements of the list are differently typed
   iv) the call to length should have [] instead of ()
-}
