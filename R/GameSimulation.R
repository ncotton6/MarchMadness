seed_stats <- read.csv(file="seed_stats.csv")

# game_simulate <- function(teamA, teamB){
#   # assign point scale 
#   # +1 if one offensive/defensive attribute is greater than other
#   # -1 if turnovers/personal fouls is greater than other
#   team_a <- 0
#   team_b <- 0
#   if( (((teamA$Wins.Losses - teamB$Wins.Losses) > 0) || ((teamA$Wins.Losses - teamB$Wins.Losses) < 0)) && (abs(teamA$Wins.Losses - teamB$Wins.Losses) <= 0.20) ) {
#       if(teamA$Wins.Losses > teamB$Wins.Losses){
#         team_a = team_a + 1
#       } else {
#         team_b = team_b + 1
#       } 
#       print("Win Loss difference: ")
#       print(abs(teamA$Wins.Losses - teamB$Wins.Losses))
#       print("TEAM_A Score")
#       print(team_a)
#       print("TEAM_B Score")
#       print(team_b)
#       if( (((teamA$"X2pts_Made.Attempt" - teamB$"X2pts_Made.Attempt") > 0) || ((teamA$"X2pts_Made.Attempt" - teamB$"X2pts_Made.Attempt") < 0)) && (abs(teamA$"X2pts_Made.Attempt" - teamB$"X2pts_Made.Attempt") <= 0.04)){
#         if(teamA$"X2pts_Made.Attempt" > teamB$"X2pts_Made.Attempt"){
#           team_a = team_a + 1
#         } else if(teamA$"X2pts_Made.Attempt" < teamB$"X2pts_Made.Attempt"){
#           team_b = team_b + 1
#         }
#         print("2 pt made attempt difference")
#         print( abs(teamA$"X2pts_Made.Attempt" - teamB$"X2pts_Made.Attempt") )
#         print("TEAM_A Score")
#         print(team_a)
#         print("TEAM_B Score")
#         print(team_b)
#         
#         if( (((teamA$"X3pts_Made.Attempt" - teamB$"X3pts_Made.Attempt") > 0) || ((teamA$"X3pts_Made.Attempt" - teamB$"X3pts_Made.Attempt") < 0)) && (abs(teamA$"X3pts_Made.Attempt" - teamB$"X3pts_Made.Attempt") <= 0.04) ){
#           if(teamA$"X3pts_Made.Attempt" > teamB$"X3pts_Made.Attempt"){
#             team_a = team_a + 1
#           } else if(teamA$"X3pts_Made.Attempt" < teamB$"X3pts_Made.Attempt"){
#             team_b = team_b + 1
#           }
#           print("3 pt made attempt difference")
#           print( abs(teamA$"X3pts_Made.Attempt" - teamB$"X3pts_Made.Attempt") )
#           print("TEAM_A Score")
#           print(team_a)
#           print("TEAM_B Score")
#           print(team_b)
#           
#           if( (((teamA$Assists.Game - teamB$Assists.Game) > 0) || ((teamA$Assists.Game - teamB$Assists.Game) < 0)) && (abs(teamA$Assists.Game - teamB$Assists.Game) <= 4)) {
#             if(teamA$Assists.Game > teamB$Assists.Game){
#               team_a = team_a + 1
#             } else if(teamA$Assists.Game < teamB$Assists.Game){
#               team_b = team_b + 1
#             }
#             print("Assists difference: ")
#             print( abs(teamA$Assists.Game - teamB$Assists.Game) )
#             print("TEAM_A Score")
#             print(team_a)
#             print("TEAM_B Score")
#             print(team_b)
#             
#             if( (((teamA$Rebounds.Game - teamB$Rebounds.Game) > 0) || (abs(teamA$Rebounds.Game - teamB$Rebounds.Game) < 0)) && (abs(teamA$Rebounds.Game - teamB$Rebounds.Game) <= 4 )) {
#               if(teamA$Rebounds.Game > teamB$Rebounds.Game){
#                 team_a = team_a + 1
#               } else if(teamA$Rebounds.Game < teamB$Rebounds.Game){
#                 team_b = team_b + 1
#               }
#               print("Rebounds.Game")
#               print( abs(teamA$Rebounds.Game - teamB$Rebounds.Game) )
#               print("TEAM_A Score")
#               print(team_a)
#               print("TEAM_B Score")
#               print(team_b)
#               
#               if( (((teamA$Turnovers.Game - teamB$Turnovers.Game) > 0) || ((teamA$Turnovers.Game - teamB$Turnovers.Game) < 0)) && (abs(teamA$Turnovers.Game - teamB$Turnovers.Game) <= 4)) {
#                 if(teamA$Turnovers.Game > teamB$Turnovers.Game){
#                   team_a = team_a - 1
#                 } else if(teamA$Turnovers.Game < teamB$Turnovers.Game){
#                   team_b = team_b - 1
#                 }
#                 print("Turnovers")
#                 print( abs(teamA$Turnovers.Game - teamB$Turnovers.Game) )
#                 
#                 print("TEAM_A Score")
#                 print(team_a)
#                 print("TEAM_B Score")
#                 print(team_b)
#                 if( (((teamA$Steals.Game - teamB$Steals.Game) > 0) || ((teamA$Steals.Game - teamB$Steals.Game) < 0)) && (abs(teamA$Steals.Game - teamB$Steals.Game) <= 2)){
#                   if(teamA$Steals.Game > teamB$Steals.Game){
#                     team_a = team_a + 1
#                   } else if(teamA$Steals.Game < teamB$Steals.Game){
#                     team_b = team_b + 1
#                   }
#                   print("Steals")
#                   print( abs(teamA$Steals.Game - teamB$Steals.Game) )
#                   print("TEAM_A Score")
#                   print(team_a)
#                   print("TEAM_B Score")
#                   print(team_b)
#                   
#                   if( (((teamA$Blocks.Game - teamB$Blocks.Game) > 0) || ((teamA$Blocks.Game - teamB$Blocks.Game) < 0)) && (abs(teamA$Blocks.Game - teamB$Blocks.Game) <= 2)){
#                     if(teamA$Blocks.Game > teamB$Blocks.Game){
#                       team_a = team_a + 1
#                     } else if(teamA$Blocks.Game < teamB$Blocks.Game){
#                       team_b = team_b + 1
#                     }
#                     print("Blocks")
#                     print( abs(teamA$Blocks.Game - teamB$Blocks.Game) )
#                     
#                     print("TEAM_A Score")
#                     print(team_a)
#                     print("TEAM_B Score")
#                     print(team_b)
#                     
# #                     if( (((teamA$FT_Made.Attempt - teamB$FT_Made.Attempt) > 0) || ((teamA$FT_Made.Attempt - teamB$FT_Made.Attempt) < 0)) && (abs(teamA$FT_Made.Attempt - teamB$FT_Made.Attempt) <= 0.04)){
# #                       if(teamA$FT_Made.Attempt > teamB$FT_Made.Attempt){
# #                         team_a = team_a + 1
# #                       } else if(teamA$FT_Made.Attempt < teamB$FT_Made.Attempt){
# #                         team_b = team_b + 1
# #                       }
# #                       print("Free Throw Made/Attempt")
# #                       print( abs(teamA$FT_Made.Attempt - teamB$FT_Made.Attempt) )
# #                       
# #                       print("TEAM_A Score")
# #                       print(team_a)
# #                       print("TEAM_B Score")
# #                       print(team_b)
# #                       
# #                       # compare team_a team_b scores
# #                       if(team_a > team_b){
# #                         return(teamA)
# #                       } else{
# #                         return(teamB)
# #                       }
# #                     } else if( (abs(teamA$FT_Made.Attempt - teamB$FT_Made.Attempt) > 0.04) && ((teamA$FT_Made.Attempt - teamB$FT_Made.Attempt) > 0)){
# #                       print(teamA)
# #                       return(teamA)
# #                     } else{
# #                       print(teamB)
# #                       return(teamB)
# #                     }
#                     
#                     # compare team_a team_b scores
#                     if(team_a > team_b){
#                       return(teamA)
#                     } else{
#                       return(teamB)
#                     }
#                   } else if( (abs(teamA$Blocks.Game - teamB$Blocks.Game) > 2) && ((teamA$Blocks.Game - teamB$Blocks.Game) > 0)){
#                     print(teamA)
#                     return(teamA)
#                   } else {
#                     print(teamB)
#                     return(teamB)
#                   }
#                 } else if( (abs(teamA$Steals.Game - teamB$Steals.Game) > 2) && ((teamA$Steals.Game - teamB$Steals.Game) > 0)){
#                   print(teamA)
#                   return(teamA)
#                 } else {
#                   print(teamB)
#                   return(teamB)
#                 }
#               } else if( (abs(teamA$Turnovers.Game - teamB$Turnovers.Game) > 4) && ((teamA$Turnovers.Game - teamB$Turnovers.Game) > 0)){
#                 print(teamB)
#                 return(teamB)
#               } else {
#                 print(teamA)
#                 return(teamA)
#               }
#             } else if( (abs(teamA$Rebounds.Game - teamB$Rebounds.Game) > 4 ) && ((teamA$Rebounds.Game - teamB$Rebounds.Game) > 0) ){
#               print(teamA)
#               return(teamA)
#             } else{
#               print(teamB)
#               return(teamB)
#             } 
#           } else if( (abs(teamA$Assists.Game - teamB$Assists.Game) > 4) && ((teamA$Assists.Game - teamB$Assists.Game) > 0)){
#             print(teamA)
#             return(teamA)
#           } else{
#             print(teamB)
#             return(teamB)
#           }
#         } else if( (abs(teamA$"X3pts_Made.Attempt" - teamB$"X3pts_Made.Attempt") > 0.04) && ((teamA$"X3pts_Made.Attempt" - teamB$"X3pts_Made.Attempt") > 0) ){
#           print(teamA)
#           return(teamA)
#         } else{
#           print(teamB)
#           return(teamB)
#         }
#       } else if( (abs(teamA$"X2pts_Made.Attempt" - teamB$"X2pts_Made.Attempt") > 0.04) && ((teamA$"X2pts_Made.Attempt" - teamB$"X2pts_Made.Attempt") > 0) ){
#         print(teamA)
#         return(teamA)
#       } else{
#         print(teamB)
#         return(teamB)
#       }
#   } else if( (abs(teamA$Wins.Losses - teamB$Wins.Losses) > 0.20) && ((teamA$Wins.Losses - teamB$Wins.Losses) > 0) ){
#     print(teamA)
#     return(teamA)
#   } else {
#     print(teamB)
#     return(teamB)
#   }
# }

game_simulate <- function(teamA, teamB){
  # assign point scale 
  # +1 if one offensive/defensive attribute is greater than other
  # -1 if turnovers/personal fouls is greater than other
  team_a <- 0
  team_b <- 0
  if( (((teamA$Wins.Losses - teamB$Wins.Losses) > 0) || ((teamA$Wins.Losses - teamB$Wins.Losses) < 0)) && (abs(teamA$Wins.Losses - teamB$Wins.Losses) <= 0.215) ) {
    if(teamA$Wins.Losses > teamB$Wins.Losses){
      team_a = team_a + 1
    } else {
      team_b = team_b + 1
    } 
    print("Win Loss difference: ")
    print(abs(teamA$Wins.Losses - teamB$Wins.Losses))
    print("TEAM_A Score")
    print(team_a)
    print("TEAM_B Score")
    print(team_b)
    
    if( (((teamA$Assists.Game - teamB$Assists.Game) > 0) || ((teamA$Assists.Game - teamB$Assists.Game) < 0)) && (abs(teamA$Assists.Game - teamB$Assists.Game) <= 4.393)){
      if(teamA$Assists.Game > teamB$Assists.Game){
        team_a = team_a + 1
      } else if(teamA$Assists.Game < teamB$Assists.Game){
        team_b = team_b + 1
      }
      print("Assists Difference: ")
      print( abs(teamA$Assists.Game - teamB$Assists.Game) )
      print("TEAM_A Score")
      print(team_a)
      print("TEAM_B Score")
      print(team_b)
      
      if( (((teamA$Turnovers.Game - teamB$Turnovers.Game) > 0) || ((teamA$Turnovers.Game - teamB$Turnovers.Game) < 0)) && (abs(teamA$Turnovers.Game - teamB$Turnovers.Game) <= 2.327) ){
        if(teamA$Turnovers > teamB$Turnovers){
          team_a = team_a - 1
        } else if(teamA$Turnovers < teamB$Turnovers){
          team_b = team_b - 1
        }
        print("Turnovers Difference: ")
        print( abs(teamA$Turnovers - teamB$Turnovers) )
        print("TEAM_A Score")
        print(team_a)
        print("TEAM_B Score")
        print(team_b)
        
        if( (((teamA$Steals.Game - teamB$Steals.Game) > 0) || ((teamA$Steals.Game - teamB$Steals.Game) < 0)) && (abs(teamA$Steals.Game - teamB$Steals.Game) <= 2.506)) {
          if(teamA$Steals.Game > teamB$Steals.Game){
            team_a = team_a + 1
          } else if(teamA$Steals.Game < teamB$Steals.Game){
            team_b = team_b + 1
          }
          print("Steals difference: ")
          print( abs(teamA$Steals.Game - teamB$Steals.Game) )
          print("TEAM_A Score")
          print(team_a)
          print("TEAM_B Score")
          print(team_b)
          
          if( (((teamA$Blocks.Game - teamB$Blocks.Game) > 0) || (abs(teamA$Blocks.Game - teamB$Blocks.Game) < 0)) && (abs(teamA$Blocks.Game - teamB$Blocks.Game) <= 2.311 )) {
            if(teamA$Blocks.Game > teamB$Blocks.Game){
              team_a = team_a + 1
            } else if(teamA$Blocks.Game < teamB$Blocks.Game){
              team_b = team_b + 1
            }
            print("Blocks Difference: ")
            print( abs(teamA$Blocks.Game - teamB$Blocks.Game) )
            print("TEAM_A Score")
            print(team_a)
            print("TEAM_B Score")
            print(team_b)
            
            if( (((teamA$"X2pts_Made.Attempt" - teamB$"X2pts_Made.Attempt") > 0) || ((teamA$"X2pts_Made.Attempt" - teamB$"X2pts_Made.Attempt") < 0)) && (abs(teamA$"X2pts_Made.Attempt" - teamB$"X2pts_Made.Attempt") <= 0.049)) {
              if(teamA$"X2pts_Made.Attempt" > teamB$"X2pts_Made.Attempt"){
                team_a = team_a + 1
              } else if(teamA$"X2pts_Made.Attempt" < teamB$"X2pts_Made.Attempt"){
                team_b = team_b + 1
              }
              print("2 points Made/Attempt Difference: ")
              print( abs(teamA$"X2pts_Made.Attempt" - teamB$"X2pts_Made.Attempt") )
              print("TEAM_A Score")
              print(team_a)
              print("TEAM_B Score")
              print(team_b)
              
              if( (((teamA$Rebounds.Game - teamB$Rebounds.Game) > 0) || ((teamA$Rebounds.Game - teamB$Rebounds.Game) < 0)) && (abs(teamA$Rebounds.Game - teamB$Rebounds.Game) <= 5.288)){
                if(teamA$Rebounds.Game > teamB$Rebounds.Game){
                  team_a = team_a + 1
                } else if(teamA$Rebounds.Game < teamB$Rebounds.Game){
                  team_b = team_b + 1
                }
                print("Rebounds Difference: ")
                print( abs(teamA$Rebounds.Game - teamB$Rebounds.Game) )
                print("TEAM_A Score")
                print(team_a)
                print("TEAM_B Score")
                print(team_b)
                
                if( (((teamA$"X3pts_Made.Attempt" - teamB$"X3pts_Made.Attempt") > 0) || ((teamA$"X3pts_Made.Attempt" - teamB$"X3pts_Made.Attempt") < 0)) && (abs(teamA$"X3pts_Made.Attempt" - teamB$"X3pts_Made.Attempt") <= 0.053) ){
                  if(teamA$"X3pts_Made.Attempt" > teamB$"X3pts_Made.Attempt"){
                    team_a = team_a + 1
                  } else if(teamA$"X3pts_Made.Attempt" < teamB$"X3pts_Made.Attempt"){
                    team_b = team_b + 1
                  }
                  print("3 pt made attempt difference: ")
                  print( abs(teamA$"X3pts_Made.Attempt" - teamB$"X3pts_Made.Attempt") )
                  print("TEAM_A Score")
                  print(team_a)
                  print("TEAM_B Score")
                  print(team_b)
                  
                  if( (((teamA$PersonalFoul.Game - teamB$PersonalFoul.Game) > 0) || ((teamA$PersonalFoul.Game - teamB$PersonalFoul.Game) < 0)) && (abs(teamA$PersonalFoul.Game - teamB$PersonalFoul.Game) <= 3.889)){
                    if(teamA$PersonalFoul.Game > teamB$PersonalFoul.Game){
                      team_a = team_a - 1
                    } else if(teamA$PersonalFoul.Game < teamB$PersonalFoul.Game){
                      team_b = team_b - 1
                    }
                    print("Personal Foul Difference: ")
                    print( abs(teamA$PersonalFoul.Game - teamB$PersonalFoul.Game) )
                    
                    print("TEAM_A Score")
                    print(team_a)
                    print("TEAM_B Score")
                    print(team_b)
                  
                    if( (((teamA$FT_Made.Attempt - teamB$FT_Made.Attempt) > 0) || ((teamA$FT_Made.Attempt - teamB$FT_Made.Attempt) < 0)) && (abs(teamA$FT_Made.Attempt - teamB$FT_Made.Attempt) <= 0.064)){
                      if(teamA$FT_Made.Attempt > teamB$FT_Made.Attempt){
                        team_a = team_a + 1
                      } else if(teamA$FT_Made.Attempt < teamB$FT_Made.Attempt){
                        team_b = team_b + 1
                      }
                      print("Free Throw Made/Attempt")
                      print( abs(teamA$FT_Made.Attempt - teamB$FT_Made.Attempt) )           
                      print("TEAM_A Score")
                      print(team_a)
                      print("TEAM_B Score")
                      print(team_b)
                                        
                      # compare team_a team_b scores
                      if(team_a > team_b){
                        return(teamA)
                      } else{
                        return(teamB)
                      }

                    } else if( (abs(teamA$FT_Made.Attempt - teamB$FT_Made.Attempt) > 0.064) && ((teamA$FT_Made.Attempt - teamB$FT_Made.Attempt) > 0)){
                      print(teamA)
                      return(teamA)
                    } else {
                      print(teamB)
                      return(teamB)
                    }
                  } else if( (abs(teamA$PersonalFoul.Game - teamB$PersonalFoul.Game) > 3.889) && ((teamA$PersonalFoul.Game - teamB$PersonalFoul.Game) > 0)){
                    print(teamB)
                    return(teamB)
                  } else {
                    print(teamA)
                    return(teamA)
                  }
                } else if( (abs(teamA$"X3pts_Made.Attempt" - teamB$"X3pts_Made.Attempt") > 0.053) && ((teamA$"X3pts_Made.Attempt" - teamB$"X3pts_Made.Attempt") > 0)){
                  print(teamA)
                  return(teamA)
                } else {
                  print(teamB)
                  return(teamB)
                }
              } else if( (abs(teamA$Rebounds.Game - teamB$Rebounds.Game) > 5.288 ) && ((teamA$Rebounds.Game - teamB$Rebounds.Game) > 0) ){
                print(teamA)
                return(teamA)
              } else{
                print(teamB)
                return(teamB)
              } 
            } else if( (abs(teamA$"X2pts_Made.Attempt" - teamB$"X2pts_Made.Attempt") > 0.049 ) && ((teamA$"X2pts_Made.Attempt" - teamB$"X2pts_Made.Attempt") > 0) ){
              print(teamA)
              return(teamA)
            } else{
              print(teamB)
              return(teamB)
            } 
          } else if( (abs(teamA$Blocks.Game - teamB$Blocks.Game) > 2.311) && ((teamA$Blocks.Game - teamB$Blocks.Game) > 0)){
            print(teamA)
            return(teamA)
          } else{
            print(teamB)
            return(teamB)
          }
        } else if( (abs(teamA$Steals.Game - teamB$Steals.Game) > 2.506) && ((teamA$Steals.Game - teamB$Steals.Game) > 0) ){
          print(teamA)
          return(teamA)
        } else{
          print(teamB)
          return(teamB)
        }
      } else if( (abs(teamA$Turnovers.Game - teamB$Turnovers.Game) > 2.327) && ((teamA$Turnovers.Game - teamB$Turnovers.Game) > 0) ){
        print(teamB)
        return(teamB)
      } else{
        print(teamA)
        return(teamA)
      }
    } else if( (abs(teamA$Assists.Game - teamB$Assists.Game) > 4.393) && ((teamA$Assists.Game - teamB$Assists.Game) > 0) ){
      print(teamA)
      return(teamA)
    } else {
      print(teamB)
      return(teamB)
    }
  } else if( (abs(teamA$Wins.Losses - teamB$Wins.Losses) > 0.215) && ((teamA$Wins.Losses - teamB$Wins.Losses) > 0) ){
    print(teamA)
    return(teamA)
  } else {
    print(teamB)
    return(teamB)
  }
}

# separate by regions
# East = W
# Midwest = X
# South = Y
# West = Z
East <- data.frame(seed_stats[0,])
Midwest <- data.frame(seed_stats[0,])
South <- data.frame(seed_stats[0,])
West <- data.frame(seed_stats[0,])

East_excp <- data.frame(seed_stats[0,])
Midwest_excp <- data.frame(seed_stats[0,])
South_excp <- data.frame(seed_stats[0,])
West_excp <- data.frame(seed_stats[0,])

Selection_Sunday_winners <- data.frame(seed_stats[0,])

# play the selection sunday games first
# put teams in corresponding regions
for(i in 1: length(seed_stats$seed)){
  if(grepl("W", seed_stats$seed[i])){
    print("IN THE EAST")
    #print(seed_stats[i,])
    if(grepl("a", seed_stats$seed[i])){
      East_excp <- rbind(East_excp, seed_stats[i,])
    } else if(grepl("b", seed_stats$seed[i])){
      East_excp <- rbind(East_excp, seed_stats[i,])
      #after the b was added play a and b
      winner <- game_simulate(East_excp[1,] , East_excp[2,])
      print("Winner:")
      print(winner)
      #add the winner of them
      East <- rbind(East, winner)
      Selection_Sunday_winners <- rbind(Selection_Sunday_winners, winner)
    } else {
      East <- rbind(East, seed_stats[i,])
    }
  }
  if(grepl("X", seed_stats$seed[i])){
    print("IN THE MIDWEST")
    #print(seed_stats[i,])
    if(grepl("a", seed_stats$seed[i])){
      Midwest_excp <- rbind(Midwest_excp, seed_stats[i,])
    } else if(grepl("b", seed_stats$seed[i])){
      Midwest_excp <- rbind(Midwest_excp, seed_stats[i,])
      #after the b was added play a and b
      winner <- game_simulate(Midwest_excp[1,] , Midwest_excp[2,])
      print("Winner:")
      print(winner)
      #add the winner of them
      Midwest <- rbind(Midwest, winner)
      Selection_Sunday_winners <- rbind(Selection_Sunday_winners, winner)
    } else {
      Midwest <- rbind(Midwest, seed_stats[i,])
    }
  }
  if(grepl("Y", seed_stats$seed[i])){
    print("IN THE SOUTH")
    #print(seed_stats[i,])
    #South <- rbind(South, seed_stats[i,])
    if(grepl("a", seed_stats$seed[i])){
      South_excp <- rbind(South_excp, seed_stats[i,])
    } else if(grepl("b", seed_stats$seed[i])){
      South_excp <- rbind(South_excp, seed_stats[i,])
      #after the b was added play a and b
      winner <- game_simulate(South_excp[1,] , South_excp[2,])
      print("Winner:")
      print(winner)
      #add the winner of them
      South <- rbind(South, winner)
      Selection_Sunday_winners <- rbind(Selection_Sunday_winners, winner)
    } else {
      South <- rbind(South, seed_stats[i,])
    }
  }
  if(grepl("Z", seed_stats$seed[i])){
    print("IN THE WEST")
    #West <- rbind(West, seed_stats[i,])
    if(grepl("a", seed_stats$seed[i])){
      West_excp <- rbind(West_excp, seed_stats[i,])
    } else if(grepl("b", seed_stats$seed[i])){
      West_excp <- rbind(West_excp, seed_stats[i,])
      #after the b was added play a and b
      winner <- game_simulate(West_excp[1,] , West_excp[2,])
      print("Winner:")
      print(winner)
      #add the winner of them
      West <- rbind(West, winner)
      Selection_Sunday_winners <- rbind(Selection_Sunday_winners, winner)
    } else {
      West <- rbind(West, seed_stats[i,])
    }
  }
}

East_round2_winners <- data.frame(seed_stats[0,])
Midwest_round2_winners <- data.frame(seed_stats[0,])
South_round2_winners <- data.frame(seed_stats[0,])
West_round2_winners <- data.frame(seed_stats[0,])

# play the 2nd round
start <- 1
end <- 16
length <- 8
for(i in 1: length){
  # play two East teams
#   print(East[start,])
#   print("VERSING")
#   print(East[end,])
  winner <- game_simulate(East[start,], East[end,])
  print("East Round 2 Winner: ")
  print(winner)
  East_round2_winners <- rbind(East_round2_winners, winner)
  
#   print("----------------------------------------------------------------------------")
  
  # play two Midwest teams
#   print(Midwest[start,])
#   print("VERSING")
#   print(Midwest[end,])
  winner <- game_simulate(Midwest[start,], Midwest[end,])
  print("Midwest Round 2 Winner: ")
  print(winner)
  Midwest_round2_winners <- rbind(Midwest_round2_winners, winner)
  
#   print("----------------------------------------------------------------------------")
  
  # play two South teams
#   print(South[start,])
#   print("VERSING")
#   print(South[end,])
  winner <- game_simulate(South[start,], South[end,])
  print("South Round 2 Winner: ")
  print(winner)
  South_round2_winners <- rbind(South_round2_winners, winner)
  
#   print("----------------------------------------------------------------------------")
  
  # play two West teams
#   print(West[start,])
#   print("VERSING")
#   print(West[end,])
  winner <- game_simulate(West[start,], West[end,])
  print("West Round 2 Winner: ")
  print(winner)
  West_round2_winners <- rbind(West_round2_winners, winner)
  
  start = start + 1
  end = end - 1
}

East_round3_winners <- data.frame(seed_stats[0,])
Midwest_round3_winners <- data.frame(seed_stats[0,])
South_round3_winners <- data.frame(seed_stats[0,])
West_round3_winners <- data.frame(seed_stats[0,])

# play the 3rd round
start <- 1
end <- 8
length <- 4
for(i in 1: length) {
  # play two teams
#   print(East_round2_winners[start,])
#   print("VERSING")
#   print(East_round2_winners[end,])
  winner <- game_simulate(East_round2_winners[start,], East_round2_winners[end,])
  print("East Round 3 Winner: ")
  print(winner)
  East_round3_winners <- rbind(East_round3_winners, winner)
  
#   print("----------------------------------------------------------------------------")
  
  # play two Midwest teams
#   print(Midwest_round2_winners[start,])
#   print("VERSING")
#   print(Midwest_round2_winners[end,])
  winner <- game_simulate(Midwest_round2_winners[start,], Midwest_round2_winners[end,])
  print("Midwest Round 3 Winner: ")
  print(winner)
  Midwest_round3_winners <- rbind(Midwest_round3_winners, winner)
  
#   print("----------------------------------------------------------------------------")
  
  # play two South teams
#   print(South_round2_winners[start,])
#   print("VERSING")
#   print(South_round2_winners[end,])
  winner <- game_simulate(South_round2_winners[start,], South_round2_winners[end,])
  print("South Round 3 Winner: ")
  print(winner)
  South_round3_winners <- rbind(South_round3_winners, winner)
  
#   print("----------------------------------------------------------------------------")
  
  # play two West teams
#   print(West_round2_winners[start,])
#   print("VERSING")
#   print(West_round2_winners[end,])
  winner <- game_simulate(West_round2_winners[start,], West_round2_winners[end,])
  print("West Round 3 Winner: ")
  print(winner)
  West_round3_winners <- rbind(West_round3_winners, winner)
  
  start = start + 1
  end = end - 1
}

East_round4_winners <- data.frame(seed_stats[0,])
Midwest_round4_winners <- data.frame(seed_stats[0,])
South_round4_winners <- data.frame(seed_stats[0,])
West_round4_winners <- data.frame(seed_stats[0,])

# play the 4th round
start <- 1
end <- 4
length <- 2
for(i in 1: length) {
  # play two teams
  winner <- game_simulate(East_round3_winners[start,], East_round3_winners[end,])
  print("East Round 4 Winner: ")
  print(winner)
  East_round4_winners <- rbind(East_round4_winners, winner)
  
#   print("----------------------------------------------------------------------------")
  
  # play two Midwest teams
  winner <- game_simulate(Midwest_round3_winners[start,], Midwest_round3_winners[end,])
  print("Midwest Round 4 Winner: ")
  print(winner)
  Midwest_round4_winners <- rbind(Midwest_round4_winners, winner)
  
#   print("----------------------------------------------------------------------------")
  
  # play two South teams
  winner <- game_simulate(South_round3_winners[start,], South_round3_winners[end,])
  print("South Round 4 Winner: ")
  print(winner)
  South_round4_winners <- rbind(South_round4_winners, winner)
  
#   print("----------------------------------------------------------------------------")
  
  # play two West teams
  winner <- game_simulate(West_round3_winners[start,], West_round3_winners[end,])
  print("West Round 4 Winner: ")
  print(winner)
  West_round4_winners <- rbind(West_round4_winners, winner)
  
  start = start + 1
  end = end - 1
}

East_round5_winners <- data.frame(seed_stats[0,])
Midwest_round5_winners <- data.frame(seed_stats[0,])
South_round5_winners <- data.frame(seed_stats[0,])
West_round5_winners <- data.frame(seed_stats[0,])
FinalFour <- data.frame(seed_stats[0,])

# play the 5th round
start <- 1
end <- 2
length <- 1
for(i in 1: length) {
  # play two teams
#   print(East_round4_winners[start,])
#   print("VERSING")
#   print(East_round4_winners[end,])
  winner <- game_simulate(East_round4_winners[start,], East_round4_winners[end,])
  print("East Round 5 Winner: ")
  print(winner)
  East_round5_winners <- rbind(East_round5_winners, winner)
  FinalFour <- rbind(FinalFour, winner)
  
  # play two Midwest teams
  winner <- game_simulate(Midwest_round4_winners[start,], Midwest_round4_winners[end,])
  print("Midwest Round 5 Winner: ")
  print(winner)
  Midwest_round5_winners <- rbind(Midwest_round5_winners, winner)
  FinalFour <- rbind(FinalFour, winner)
  
  # play two South teams
  winner <- game_simulate(South_round4_winners[start,], South_round4_winners[end,])
  print("South Round 5 Winner: ")
  print(winner)
  South_round5_winners <- rbind(South_round5_winners, winner)
  FinalFour <- rbind(FinalFour, winner)
  
  # play two West teams
  winner <- game_simulate(West_round4_winners[start,], West_round4_winners[end,])
  print("West Round 5 Winner: ")
  print(winner)
  West_round5_winners <- rbind(West_round5_winners, winner)
  FinalFour <- rbind(FinalFour, winner)
  
  start = start + 1
  end = end - 1
}

Championship <- data.frame(seed_stats[0,])

# play final four
# play the first two teams in final four
print(FinalFour[1,])
print("VERSING")
print(FinalFour[2,])
winner <- game_simulate(FinalFour[1,], FinalFour[2,])
Championship <- rbind(Championship, winner)

# play the last two teams in final four
print(FinalFour[3,])
print("VERSING")
print(FinalFour[4,])
winner <- game_simulate(FinalFour[3,], FinalFour[4,])
Championship <- rbind(Championship, winner)

Champion <- data.frame(seed_stats[0,])
# play the championship game
print(Championship[1,])
print("VERSING")
print(Championship[2,])
winner <- game_simulate(Championship[1,], Championship[2,])
Champion <- rbind(Champion, winner)
