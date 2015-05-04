# read season stats, teams, and tournament seeding
gamestats <- read.csv(file="regular_season_detailed_results_2015.csv")
teams <- read.csv(file="teams.csv")
tournament <- read.csv(file="tourney_seeds_2015.csv")

# initialize teams
teams["No.Games"] <- NA
teams["wins"] <- NA
teams["losses"] <- NA
teams["Wins/Losses"] <- NA
teams["2pts_Made/Attempt"] <- NA
teams["3pts_Made/Attempt"] <- NA
teams["FT_Made/Attempt"] <- NA
teams["Rebounds"] <- NA
teams["Rebounds/Game"] <- NA
teams["Assists"] <- NA
teams["Turnovers"] <- NA
teams["Steals"] <- NA
teams["Blocks"] <- NA
teams["PersonalFoul"] <- NA

# loop thru team list
for(i in 1:length(teams$team_id)){
  # go thru team wins in every game
  # get the numbers for each game stats
  team_wins = 0
  team_losses = 0
  tfgm = 0
  tfga = 0
  tfgm3 = 0
  tfga3 = 0
  tftm = 0
  tfta = 0
  trebounds = 0
  tast = 0
  tto = 0
  tstl = 0
  tblk = 0
  tpf = 0
  # loop to find when team was a winner
  for(j in 1:length(gamestats$wteam)){
    # count how many times you see this team wins
    if(teams$team_id[i] == gamestats$wteam[j]){
      team_wins = team_wins + 1
      tfgm = tfgm + gamestats$wfgm[j]
      tfga = tfga + gamestats$wfga[j]
      tfgm3 = tfgm3 + gamestats$wfgm3[j]
      tfga3 = tfga3 + gamestats$wfga3[j]
      tftm = tftm + gamestats$wftm[j]
      tfta = tfta + gamestats$wfta[j]
      trebounds = trebounds + gamestats$wor[j] + gamestats$wdr[j]
      tast = tast + gamestats$wast[j]
      tto = tto + gamestats$wto[j]
      tstl = tstl + gamestats$wstl[j]
      tblk = tblk + gamestats$wblk[j]
      tpf = tpf + gamestats$wpf[j]
    }
  }
  # loop to see when team was a loser
  for(j in 1:length(gamestats$lteam)){
    # with one team loop to find losses
    if(teams$team_id[i] == gamestats$lteam[j]){
      team_losses = team_losses + 1
      tfgm = tfgm + gamestats$lfgm[j]
      tfga = tfga + gamestats$lfga[j]
      tfgm3 = tfgm3 + gamestats$lfgm3[j]
      tfga3 = tfga3 + gamestats$lfga3[j]
      tftm = tftm + gamestats$lftm[j]
      tfta = tfta + gamestats$lfta[j]
      trebounds = trebounds + gamestats$lor[j] + gamestats$ldr[j]
      twast = tast + gamestats$last[j]
      twto = tto + gamestats$lto[j]
      tstl = tstl + gamestats$lstl[j]
      tblk = tblk + gamestats$lblk[j]
      tpf = tpf + gamestats$lpf[j]
    }
  }
    
  teams$"No.Games"[i] <- team_wins + team_losses
  #add to teams table wins column
  w_l_ratio <- team_wins / (team_wins + team_losses)
  teams$wins[i] <- team_wins
  teams$losses[i] <- team_losses
  teams$"Wins/Losses"[i] <- w_l_ratio
  
  #number of games
  number_games = (team_wins + team_losses)
  
  # get ratios
  tfgm_tfga_ratio = tfgm / tfga
  tfgm3_tfga3_ratio = tfgm3 / tfga3
  tftm_tfta_ratio = tftm / tfta
  
  teams$"2pts_Made/Attempt"[i] = tfgm_tfga_ratio
  teams$"3pts_Made/Attempt"[i] = tfgm3_tfga3_ratio
  teams$"FT_Made/Attempt"[i] = tftm_tfta_ratio
  teams$Rebounds[i] = trebounds
  teams$"Rebounds/Game"[i] = trebounds/number_games
  teams$Assists[i] = tast/number_games
  teams$Turnovers[i] = tto/number_games
  teams$Steals[i] = tstl/number_games
  teams$Blocks[i] = tblk/number_games
  teams$PersonalFoul[i] = tpf/number_games
}

#tournament
tournament["name"] <- NA
tournament["wins"] <- NA
tournament["losses"] <- NA
tournament["Wins/Losses"] <- NA
tournament["2pts_Made/Attempt"] <- NA
tournament["3pts_Made/Attempt"] <- NA
tournament["FT_Made/Attempt"] <- NA
#tournament["Rebounds"] <- NA
tournament["Rebounds/Game"] <- NA
tournament["Assists/Game"] <- NA
tournament["Turnovers/Game"] <- NA
tournament["Steals/Game"] <- NA
tournament["Blocks/Game"] <- NA
tournament["PersonalFoul/Game"] <- NA

for(k in 1:length(tournament$team)){
 for(m in 1:length(teams$team_id)){
   if(tournament$team[k] == teams$team_id[m]){
     tournament$name[k] = as.character(teams$team_name[m])
     tournament$wins[k] = teams$wins[m]
     tournament$losses[k] = teams$losses[m]
     tournament$"Wins/Losses"[k] = teams$"Wins/Losses"[m]
     tournament$"2pts_Made/Attempt"[k] = teams$"2pts_Made/Attempt"[m]
     tournament$"3pts_Made/Attempt"[k] = teams$"3pts_Made/Attempt"[m]
     tournament$"FT_Made/Attempt"[k] = teams$"FT_Made/Attempt"[m]
     #tournament$Rebounds[k] = teams$Rebounds[m]
     tournament$"Rebounds/Game"[k] = teams$"Rebounds/Game"[m]
     tournament$"Assists/Game"[k] = teams$Assists[m]
     tournament$"Turnovers/Game"[k] = teams$Turnovers[m]
     tournament$"Steals/Game"[k] = teams$Steals[m]
     tournament$"Blocks/Game"[k] = teams$Blocks[m]
     tournament$"FT_Made/Attempt"[k] = teams$"FT_Made/Attempt"[m]
     tournament$"PersonalFoul/Game"[k] = teams$PersonalFoul[m]
   }
 }
}


# write out to csv file
seed_stats <- write.csv(tournament, file="seed_stats.csv")
