reverseOrdered::[Int] -> Bool
reverseOrdered [] = False
reverseOrdered [x] = True
reverseOrdered (x:xs)
    | x > head(xs) = reverseOrdered(xs)
    | otherwise = False 